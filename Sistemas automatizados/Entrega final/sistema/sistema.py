"""Executa a solução completa:

  planta (servidor Modbus TCP)  ⇄  CLP virtual (cliente Modbus TCP)  ⇄  supervisão web

    python sistema.py               # tudo junto; abra http://localhost:8080
    python sistema.py --so-planta   # só a planta (para ligar um CLP OpenPLC externo)

A troca entre CLP e planta passa de fato por TCP (127.0.0.1:5020).
"""
from __future__ import annotations

import argparse
import asyncio
import json
import logging
import threading
import time
from dataclasses import asdict
from http.server import BaseHTTPRequestHandler, ThreadingHTTPServer
from pathlib import Path

from pymodbus.client import AsyncModbusTcpClient
from pymodbus.datastore import (ModbusSequentialDataBlock, ModbusServerContext,
                                ModbusSlaveContext)
from pymodbus.server import StartAsyncTcpServer

import config as C
from controlador import Comandos, Controlador, ImagemEntradas
from planta import Falhas, Planta

logging.getLogger("pymodbus").setLevel(logging.CRITICAL)
LOCK = threading.Lock()
WEB = Path(__file__).with_name("web")


def _bloco():
    return ModbusSequentialDataBlock(0, [0] * 16)


# =========================================================== PLANTA (servidor)
class PlantaServidor:
    def __init__(self, planta: Planta):
        self.planta = planta
        self.slave = ModbusSlaveContext(di=_bloco(), co=_bloco(), ir=_bloco(), hr=_bloco())
        self.contexto = ModbusServerContext(slaves=self.slave, single=True)
        self._hb_clp = -1
        self._t_hb_clp = time.monotonic()

    async def ciclo(self):
        while True:
            with LOCK:
                p, s = self.planta, self.slave
                p.bomba_cmd = bool(s.getValues(1, C.CO_BOMBA)[0])
                p.valvula_cmd = bool(s.getValues(1, C.CO_VALVULA)[0])
                hb = s.getValues(3, C.HR_HB_CLP)[0]
                agora = time.monotonic()
                if hb != self._hb_clp:
                    self._hb_clp, self._t_hb_clp = hb, agora
                clp_ativo = agora - self._t_hb_clp < C.TIMEOUT_COMUNICACAO
                p.passo(C.PERIODO_PLANTA, clp_ativo)
                if not p.falhas.congelar_comunicacao:      # dados "antigos" quando congelado
                    s.setValues(2, C.DI_AGUA_OK, [p.agua, p.valvula_aberta, p.fluxo])
                    s.setValues(4, C.IR_UMIDADE_uA, [p.corrente_ua(), p.hb])
            await asyncio.sleep(C.PERIODO_PLANTA)

    async def servir(self):
        await StartAsyncTcpServer(context=self.contexto,
                                  address=(C.MODBUS_HOST, C.MODBUS_PORTA))


# =========================================================== CLP (cliente)
class ClpRuntime:
    def __init__(self, ctrl: Controlador):
        self.ctrl = ctrl
        self.pendentes = Comandos()
        self.entradas: ImagemEntradas | None = None
        self.leituras_ok = 0
        self.leituras_erro = 0
        self._hb_planta = -1
        self._t_hb_planta = time.monotonic()
        self._hb = 0
        self.t0 = time.monotonic()
        self.historico: list[dict] = []
        self._t_hist = 0.0

    def comandar(self, acao: str):
        with LOCK:
            setattr(self.pendentes, acao, True)

    async def executar(self):
        cli = AsyncModbusTcpClient(C.MODBUS_HOST, port=C.MODBUS_PORTA, timeout=1, retries=0)
        di, ir = [False, False, False], [0, 0]
        try:
            await self._laco(cli, di, ir)
        finally:
            cli.close()

    async def _laco(self, cli, di, ir):
        while True:
            inicio = time.monotonic()
            # ---- 1. leitura de entradas pela rede
            try:
                if not cli.connected:
                    await cli.connect()
                r1 = await cli.read_discrete_inputs(C.DI_AGUA_OK, count=3, slave=C.MODBUS_UNIDADE)
                r2 = await cli.read_input_registers(C.IR_UMIDADE_uA, count=2, slave=C.MODBUS_UNIDADE)
                if r1.isError() or r2.isError():
                    raise IOError("resposta Modbus com erro")
                di, ir = r1.bits[:3], r2.registers[:2]
                self.leituras_ok += 1
            except Exception:
                self.leituras_erro += 1
            agora = time.monotonic()
            if ir[1] != self._hb_planta:
                self._hb_planta, self._t_hb_planta = ir[1], agora
            com_ok = agora - self._t_hb_planta < C.TIMEOUT_COMUNICACAO
            if self.leituras_ok == 0 and com_ok:
                # partida: sem imagem válida ainda; não executa a lógica (saídas em 0)
                await asyncio.sleep(C.PERIODO_SCAN)
                continue

            # ---- 2. memória-imagem + scan
            img = ImagemEntradas(agua_ok=bool(di[0]), valvula_aberta=bool(di[1]),
                                 fluxo_ok=bool(di[2]), corrente_ma=ir[0] / 1000,
                                 comunicacao_ok=com_ok)
            with LOCK:
                cmd, self.pendentes = self.pendentes, Comandos()
                t = agora - self.t0
                bomba, valvula = self.ctrl.scan(img, cmd, t)   # comando dura um scan (pulso)
                self.entradas = img
                self._hb = (self._hb + 1) % 65536
                if t - self._t_hist >= 0.5:
                    self._t_hist = t
                    self.historico.append({"t": round(t, 1), "u": round(self.ctrl.umidade, 2),
                                           "b": bomba, "v": valvula, "e": self.ctrl.etapa})
                    del self.historico[:-360]

            # ---- 3. escrita das saídas
            try:
                if cli.connected:
                    await cli.write_coils(C.CO_BOMBA, [bomba, valvula], slave=C.MODBUS_UNIDADE)
                    await cli.write_registers(C.HR_HB_CLP, [
                        self._hb, self.ctrl.etapa, self.ctrl.alarme,
                        round(self.ctrl.umidade * 10)], slave=C.MODBUS_UNIDADE)
            except Exception:
                self.leituras_erro += 1
            await asyncio.sleep(max(0.0, C.PERIODO_SCAN - (time.monotonic() - inicio)))


# =========================================================== SUPERVISÃO (HTTP)
def criar_handler(planta: Planta, clp: ClpRuntime | None):
    class H(BaseHTTPRequestHandler):
        def log_message(self, *a):
            pass

        def _json(self, obj, code=200):
            data = json.dumps(obj, ensure_ascii=False).encode()
            self.send_response(code)
            self.send_header("Content-Type", "application/json; charset=utf-8")
            self.send_header("Content-Length", str(len(data)))
            self.end_headers()
            self.wfile.write(data)

        def do_GET(self):
            if self.path in ("/", "/index.html"):
                data = (WEB / "index.html").read_bytes()
                self.send_response(200)
                self.send_header("Content-Type", "text/html; charset=utf-8")
                self.send_header("Content-Length", str(len(data)))
                self.end_headers()
                self.wfile.write(data)
            elif self.path.startswith("/api/estado"):
                self._json(estado(planta, clp))
            else:
                self._json({"erro": "não encontrado"}, 404)

        def do_POST(self):
            n = int(self.headers.get("Content-Length") or 0)
            corpo = json.loads(self.rfile.read(n) or b"{}")
            if self.path == "/api/comando" and clp and corpo.get("acao") in ("iniciar", "parar", "reset"):
                clp.comandar(corpo["acao"])
                return self._json({"ok": True})
            if self.path == "/api/falha":
                with LOCK:
                    if corpo.get("limpar"):
                        planta.falhas = Falhas()
                    for k, v in corpo.items():
                        if hasattr(planta.falhas, k):
                            setattr(planta.falhas, k, v)
                return self._json({"ok": True, "falhas": asdict(planta.falhas)})
            if self.path == "/api/umidade":
                with LOCK:
                    planta.umidade = max(0.0, min(100.0, float(corpo["valor"])))
                return self._json({"ok": True})
            self._json({"erro": "requisição inválida"}, 400)
    return H


def estado(planta: Planta, clp: ClpRuntime | None) -> dict:
    with LOCK:
        p = planta
        r = {
            "planta": {
                "umidade_real": round(p.umidade, 2), "corrente_ma": round(p.corrente_ma(), 3),
                "agua": p.agua, "valvula_aberta": p.valvula_aberta, "fluxo": p.fluxo,
                "posicao_valvula": round(p.posicao_valvula, 2),
                "bomba": p.bomba_efetiva, "valvula_energizada": p.valvula_efetiva,
                "modo_seguro": p.modo_seguro, "volume_l": round(p.volume_l, 2),
                "falhas": asdict(p.falhas),
            },
            "parametros": {"limiar": C.LIMIAR_IRRIGAR, "meta": C.META_IRRIGACAO},
        }
        if clp:
            c = clp.ctrl
            e = clp.entradas
            r["clp"] = {
                "etapa": c.etapa, "etapa_nome": C.NOMES_ETAPA[c.etapa],
                "alarme": c.alarme, "alarme_texto": C.ALARMES[c.alarme],
                "bomba": c.bomba, "valvula": c.valvula, "umidade": round(c.umidade, 2),
                "ciclo": c.ciclo, "ciclos_irrigados": c.ciclos_irrigados,
                "ciclos_dispensados": c.ciclos_dispensados, "ciclos_cancelados": c.ciclos_cancelados,
                "ultima_duracao": round(c.ultima_duracao, 1),
                "falhas_por_causa": {C.ALARMES[k]: v for k, v in c.falhas_por_causa.items() if v},
                "permissivos": c.permissivos_partida(e) if e else {},
                "comunicacao_ok": e.comunicacao_ok if e else False,
                "leituras_ok": clp.leituras_ok, "leituras_erro": clp.leituras_erro,
                "entradas": asdict(e) if e else None,
                "eventos": [asdict(ev) for ev in c.eventos[-60:]][::-1],
                "historico": clp.historico,
            }
        return r


# =========================================================== main
async def principal(so_planta: bool):
    planta = Planta()
    srv = PlantaServidor(planta)
    clp = None if so_planta else ClpRuntime(Controlador())
    web = ThreadingHTTPServer(("0.0.0.0", C.HTTP_PORTA), criar_handler(planta, clp))
    threading.Thread(target=web.serve_forever, daemon=True).start()
    print(f"Planta Modbus TCP em {C.MODBUS_HOST}:{C.MODBUS_PORTA}")
    if clp:
        print(f"Supervisão em http://localhost:{C.HTTP_PORTA}")
    tarefas = [srv.servir(), srv.ciclo()]
    if clp:
        tarefas.append(clp.executar())
    await asyncio.gather(*tarefas)


if __name__ == "__main__":
    ap = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    ap.add_argument("--so-planta", action="store_true", help="executa só a planta Modbus")
    try:
        asyncio.run(principal(ap.parse_args().so_planta))
    except KeyboardInterrupt:
        pass
