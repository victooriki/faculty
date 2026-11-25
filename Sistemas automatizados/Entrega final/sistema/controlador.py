"""Lógica do CLP virtual — GRAFCET E0..E5 + EF.

Implementação didática de um ciclo de varredura (scan) de CLP:
  1. copia as entradas para a memória-imagem (T05);
  2. avalia transições APENAS a partir da etapa ativa (T04);
  3. calcula as saídas a partir da etapa ativa (ações contínuas);
  4. devolve a imagem de saída, que só então é escrita no campo.

A classe não conhece rede nem relógio: recebe o tempo `t` (s) a cada scan,
o que permite testar com tempo simulado. O mesmo algoritmo está em
Structured Text (IEC 61131-3) em ../openplc/irrigacao.st.
"""
from __future__ import annotations

from dataclasses import dataclass, field

import config as C


@dataclass(frozen=True)
class ImagemEntradas:
    """Memória-imagem de entradas: congelada durante todo o scan."""
    agua_ok: bool
    valvula_aberta: bool
    fluxo_ok: bool
    corrente_ma: float
    comunicacao_ok: bool


@dataclass
class Comandos:
    """Comandos da supervisão (UI_INICIAR, UI_PARAR, UI_RESET)."""
    iniciar: bool = False
    parar: bool = False
    reset: bool = False


@dataclass
class Evento:
    t: float
    tipo: str
    texto: str
    ciclo: int


@dataclass
class Controlador:
    etapa: int = 0
    alarme: int = 0
    bomba: bool = False
    valvula: bool = False
    umidade: float = 0.0
    ciclo: int = 0                       # identificador do ciclo corrente
    ciclos_irrigados: int = 0
    ciclos_dispensados: int = 0
    ciclos_cancelados: int = 0
    falhas_por_causa: dict = field(default_factory=lambda: {k: 0 for k in C.ALARMES if k})
    eventos: list = field(default_factory=list)
    ultima_duracao: float = 0.0
    _t_etapa: float = 0.0                # instante de entrada na etapa ativa
    _t_inicio_ciclo: float = 0.0
    _fluxo_confirmado: bool = False
    _irrigou: bool = False
    _ciclo_registrado: int = -1          # evita registro duplicado em E5 (R07)
    _ant: Comandos = field(default_factory=Comandos)

    # ------------------------------------------------------------------ utilidades
    def _log(self, t: float, tipo: str, texto: str) -> None:
        self.eventos.append(Evento(round(t, 2), tipo, texto, self.ciclo))
        del self.eventos[:-300]

    def _ir(self, nova: int, t: float) -> None:
        if nova != self.etapa:
            self._log(t, "ETAPA", f"{C.NOMES_ETAPA[self.etapa]} → {C.NOMES_ETAPA[nova]}")
        self.etapa = nova
        self._t_etapa = t

    def _falhar(self, causa: int, t: float) -> None:
        self.alarme = causa
        self.falhas_por_causa[causa] += 1
        self._log(t, "FALHA", f"[{causa}] {C.ALARMES[causa]}")
        self._ir(C.ETAPA_EF, t)

    @staticmethod
    def repouso_coerente(e: ImagemEntradas) -> bool:
        return not e.valvula_aberta and not e.fluxo_ok

    def permissivos_partida(self, e: ImagemEntradas) -> dict:
        """R01 — condições para aceitar Iniciar (mostradas na supervisão)."""
        return {
            "Água disponível": e.agua_ok,
            "Sinal 4–20 mA válido": C.sinal_valido(e.corrente_ma),
            "Comunicação atualizada": e.comunicacao_ok,
            "Sem alarme retido": self.alarme == 0,
            "Válvula fechada / sem fluxo": self.repouso_coerente(e),
        }

    # ------------------------------------------------------------------ scan
    def scan(self, e: ImagemEntradas, cmd: Comandos, t: float) -> tuple[bool, bool]:
        # Bordas de subida dos comandos (um comando mantido não repete a ação)
        iniciar = cmd.iniciar and not self._ant.iniciar
        parar = cmd.parar and not self._ant.parar
        reset = cmd.reset and not self._ant.reset
        self._ant = Comandos(cmd.iniciar, cmd.parar, cmd.reset)

        sinal_ok = C.sinal_valido(e.corrente_ma)
        if sinal_ok:
            self.umidade = C.corrente_para_umidade(e.corrente_ma)
        dt = t - self._t_etapa
        s = self.etapa

        # ---------- falhas globais (prioridade sobre parada e transições)
        if s != C.ETAPA_EF:
            if not e.comunicacao_ok:
                self._falhar(10, t)
            elif not sinal_ok:
                self._falhar(1, t)
            elif s in (1, 2, 3) and not e.agua_ok:
                self._falhar(2, t)

        s = self.etapa
        # ---------- parada (R04): só de E1 a E4, sem retomar automaticamente
        if s in (1, 2, 3, 4) and parar:
            self.ciclos_cancelados += 1
            self._log(t, "CANCELADO", f"Ciclo {self.ciclo} cancelado pelo operador")
            self._ir(0, t)
            s = self.etapa

        # ---------- transições: somente a partir da etapa ativa (R07 / T04)
        if s == 0:
            if iniciar:
                p = self.permissivos_partida(e)
                if all(p.values()):
                    self.ciclo += 1
                    self._t_inicio_ciclo = t
                    self._irrigou = False
                    self._fluxo_confirmado = False
                    self._log(t, "INICIAR", f"Ciclo {self.ciclo} iniciado (U = {self.umidade:.1f}%)")
                    self._ir(1, t)
                else:
                    faltam = ", ".join(k for k, v in p.items() if not v)
                    self._log(t, "BLOQUEADO", f"Iniciar recusado: {faltam}")

        elif s == 1:
            if self.umidade < C.LIMIAR_IRRIGAR:
                self._ir(2, t)
            else:
                self._ir(5, t)

        elif s == 2:
            if e.valvula_aberta:
                self._ir(3, t)
            elif dt >= C.T_ABERTURA_VALVULA:
                self._falhar(3, t)

        elif s == 3:
            self._irrigou = True
            if not e.valvula_aberta:
                self._falhar(7, t)
            elif e.fluxo_ok:
                self._fluxo_confirmado = True
            elif self._fluxo_confirmado:
                self._falhar(5, t)
            elif dt >= C.T_PARTIDA_FLUXO:
                self._falhar(4, t)
            if self.etapa == 3:
                if self._fluxo_confirmado and self.umidade >= C.META_IRRIGACAO:
                    self._ir(4, t)
                elif dt >= C.T_MAX_IRRIGACAO:
                    self._falhar(6, t)

        elif s == 4:
            if dt >= C.T_RETORNO and not self.repouso_coerente(e):
                self._falhar(8, t)
            elif dt >= C.T_ESTABILIZACAO:
                if self.umidade >= C.LIMIAR_IRRIGAR:
                    self._ir(5, t)
                else:
                    self._falhar(9, t)

        elif s == 5:
            if self._ciclo_registrado != self.ciclo:       # registro único (R07)
                self._ciclo_registrado = self.ciclo
                self.ultima_duracao = t - self._t_inicio_ciclo
                if self._irrigou:
                    self.ciclos_irrigados += 1
                    res = "irrigado"
                else:
                    self.ciclos_dispensados += 1
                    res = "dispensado (umidade suficiente)"
                self._log(t, "CONCLUIDO",
                          f"Ciclo {self.ciclo} {res} em {self.ultima_duracao:.1f} s; U = {self.umidade:.1f}%")
            self._ir(0, t)

        elif s == C.ETAPA_EF:
            if reset:
                causa_corrigida = (
                    e.comunicacao_ok and sinal_ok
                    and (self.alarme != 2 or e.agua_ok)
                )
                if causa_corrigida and self.repouso_coerente(e):
                    self._log(t, "RESET", f"Alarme [{self.alarme}] reconhecido; retorno a E0")
                    self.alarme = 0
                    self._ir(0, t)
                else:
                    self._log(t, "BLOQUEADO", "Reset recusado: causa ativa ou sinais fora do repouso")

        # ---------- ações contínuas associadas à etapa ativa
        self.valvula = self.etapa in (2, 3)
        self.bomba = self.etapa == 3 and e.agua_ok and e.valvula_aberta   # R02
        return self.bomba, self.valvula
