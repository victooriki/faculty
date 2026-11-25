"""Planta virtual: canteiro, reservatório, bomba, válvula NF e sensores.

Dinâmica simplificada e acelerada (não é calibração agronômica):
  * umidade sobe GANHO_IRRIGACAO %/s quando há bomba + válvula aberta + água + fluxo;
  * umidade cai TAXA_SECAGEM %/s com os atuadores desligados;
  * válvula NF abre ~0,8 s após energizar e retorna fechada 1 s após desenergizar;
  * fluxo aparece 0,5 s após bomba ligada com válvula aberta e água.

O modo de teste permite injetar falhas (seção `Falhas`).
"""
from __future__ import annotations

from dataclasses import dataclass, field

import config as C

GANHO_IRRIGACAO = 1.0      # %VWC/s
TAXA_SECAGEM = 0.05        # %VWC/s
T_ABRIR = 0.8              # s
T_FECHAR = 1.0             # s
T_FLUXO = 0.5              # s


@dataclass
class Falhas:
    """Falhas injetáveis pela aba de testes da supervisão."""
    sem_agua: bool = False              # boia indica reservatório vazio
    valvula_travada: bool = False       # válvula não abre
    linha_obstruida: bool = False       # sem fluxo mesmo com bomba ligada
    perder_fluxo_em: float = -1.0       # s de bomba ligada até perder o fluxo (-1 = nunca)
    sensor_valvula_preso: bool = False  # indicação de aberta não retorna
    corrente_forcada_ma: float | None = None   # ex.: 0 mA (fio rompido) ou 22 mA
    congelar_comunicacao: bool = False  # planta para de atualizar o contador de vida


@dataclass
class Planta:
    umidade: float = 28.0               # % VWC
    bomba_cmd: bool = False             # comandos recebidos do CLP (coils)
    valvula_cmd: bool = False
    bomba_efetiva: bool = False         # após o watchdog da planta
    valvula_efetiva: bool = False
    posicao_valvula: float = 0.0        # 0 fechada .. 1 aberta
    valvula_aberta: bool = False
    fluxo: bool = False
    agua: bool = True
    hb: int = 0
    modo_seguro: bool = False           # CLP sem atualizar → atuadores desligados
    volume_l: float = 0.0               # volume bombeado (indicador didático)
    falhas: Falhas = field(default_factory=Falhas)
    _t_bomba_fluxo: float = 0.0
    _t_bomba_ligada: float = 0.0

    def corrente_ma(self) -> float:
        if self.falhas.corrente_forcada_ma is not None:
            return self.falhas.corrente_forcada_ma
        return C.umidade_para_corrente(max(0.0, min(100.0, self.umidade)))

    def corrente_ua(self) -> int:
        return max(0, min(65535, round(self.corrente_ma() * 1000)))

    def passo(self, dt: float, clp_ativo: bool = True) -> None:
        f = self.falhas
        self.modo_seguro = not clp_ativo
        self.bomba_efetiva = self.bomba_cmd and clp_ativo
        self.valvula_efetiva = self.valvula_cmd and clp_ativo
        self.agua = not f.sem_agua

        # válvula normalmente fechada
        if self.valvula_efetiva and not f.valvula_travada:
            self.posicao_valvula = min(1.0, self.posicao_valvula + dt / T_ABRIR)
        else:
            self.posicao_valvula = max(0.0, self.posicao_valvula - dt / T_FECHAR)
        self.valvula_aberta = self.posicao_valvula >= 0.999 or (
            f.sensor_valvula_preso and self.valvula_aberta)

        # fluxo na linha
        bombeando = self.bomba_efetiva and self.agua and self.posicao_valvula >= 0.999
        if bombeando:
            self._t_bomba_fluxo += dt
            self._t_bomba_ligada += dt
        else:
            self._t_bomba_fluxo = 0.0
            self._t_bomba_ligada = 0.0
        perdeu = f.perder_fluxo_em >= 0 and self._t_bomba_ligada >= f.perder_fluxo_em
        self.fluxo = bombeando and self._t_bomba_fluxo >= T_FLUXO and not f.linha_obstruida and not perdeu

        # umidade do solo
        if self.fluxo:
            self.umidade += GANHO_IRRIGACAO * dt
            self.volume_l += 0.2 * dt          # 12 L/min nominais
        else:
            self.umidade -= TAXA_SECAGEM * dt
        self.umidade = max(0.0, min(100.0, self.umidade))

        if not f.congelar_comunicacao:
            self.hb = (self.hb + 1) % 65536
