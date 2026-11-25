"""T09 — comunicação Modbus TCP real (planta servidor ⇄ CLP cliente), em tempo real."""
import asyncio
import time

import config as C
import sistema
from controlador import Controlador
from planta import Planta


async def _esperar(cond, limite):
    t0 = time.monotonic()
    while time.monotonic() - t0 < limite:
        if cond():
            return time.monotonic() - t0
        await asyncio.sleep(0.05)
    raise AssertionError("condição não atingida")


async def _cenario(registro):
    planta = Planta(umidade=25.0)
    srv = sistema.PlantaServidor(planta)
    clp = sistema.ClpRuntime(Controlador())
    tarefas = [asyncio.create_task(srv.servir()), asyncio.create_task(srv.ciclo())]
    await asyncio.sleep(0.5)
    t_clp = asyncio.create_task(clp.executar())
    try:
        await _esperar(lambda: clp.leituras_ok > 10 and clp.entradas.comunicacao_ok, 5)
        # 1) comando atravessa a rede: Iniciar → coils → bomba efetiva na planta
        clp.comandar("iniciar")
        dt = await _esperar(lambda: planta.bomba_efetiva and planta.fluxo, 6)
        registro["ida"] = f"bomba e fluxo na planta {dt:.1f} s após Iniciar"
        # 2) dados congelados → CLP detecta em ~3 s e desliga as saídas
        planta.falhas.congelar_comunicacao = True
        dt = await _esperar(lambda: clp.ctrl.alarme == 10, 5)
        await asyncio.sleep(0.3)
        registro["congelar"] = (f"alarme 10 em {dt:.1f} s; DO_BOMBA={clp.ctrl.bomba}; "
                                f"bomba na planta={planta.bomba_efetiva}")
        assert 2.5 <= dt <= 3.8 and not clp.ctrl.bomba and not planta.bomba_efetiva
        # 3) reconexão exige reset; não retoma sozinho
        planta.falhas.congelar_comunicacao = False
        await asyncio.sleep(1.5)
        assert clp.ctrl.etapa == C.ETAPA_EF
        clp.comandar("reset")
        await _esperar(lambda: clp.ctrl.etapa == 0, 2)
        registro["reset"] = "após restabelecer, permaneceu em EF até Reset; voltou a E0 sem irrigar"
        # 4) CLP para de atualizar → planta entra em modo seguro com atuadores desligados
        planta.umidade = 25.0
        clp.comandar("iniciar")
        await _esperar(lambda: planta.bomba_efetiva, 6)
        t_clp.cancel()
        dt = await _esperar(lambda: planta.modo_seguro, 5)
        registro["watchdog"] = (f"CLP parado com bomba ligada → planta em modo seguro em {dt:.1f} s; "
                                f"bomba={planta.bomba_efetiva}, válvula={planta.valvula_efetiva}")
        assert not planta.bomba_efetiva and not planta.valvula_efetiva
        registro["leituras"] = f"{clp.leituras_ok} leituras Modbus bem-sucedidas"
    finally:
        t_clp.cancel()
        from pymodbus.server import ServerAsyncStop
        await ServerAsyncStop()
        for t in tarefas:
            t.cancel()


def test_T09_comunicacao(evidencia, monkeypatch):
    monkeypatch.setattr(C, "MODBUS_PORTA", 5031)
    reg = {}
    try:
        asyncio.run(_cenario(reg))
    finally:
        evidencia("T09", "Planta (servidor) e CLP (cliente) via Modbus TCP 127.0.0.1",
                  "Iniciar; congelar dados da planta; Reset; parar o CLP com bomba ligada",
                  "Comando chega à planta; falha 10 em ~3 s com saídas desligadas; "
                  "Reset obrigatório; planta desliga atuadores sem CLP",
                  "; ".join(reg.values()))
