"""Infraestrutura de teste: simulação acoplada planta ⇄ CLP em tempo simulado
e coleta de evidências (condição inicial, entradas, esperado, observado)."""
import sys
from pathlib import Path

import pytest

RAIZ = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(RAIZ))

import config as C  # noqa: E402
from controlador import Comandos, Controlador, ImagemEntradas  # noqa: E402
from planta import Planta  # noqa: E402

EVIDENCIAS: list[dict] = []


class Sim:
    """Acopla planta e CLP sem rede, com passo fixo de 0,1 s."""

    def __init__(self, umidade=25.0):
        self.planta = Planta(umidade=umidade)
        self.ctrl = Controlador()
        self.t = 0.0
        self.com_ok = True
        self.violacoes_r02 = 0        # bomba ligada sem válvula aberta/sem água
        self.etapas = [0]

    def imagem(self) -> ImagemEntradas:
        p = self.planta
        return ImagemEntradas(p.agua, p.valvula_aberta, p.fluxo, p.corrente_ma(), self.com_ok)

    def passo(self, cmd: Comandos | None = None):
        img = self.imagem()
        b, v = self.ctrl.scan(img, cmd or Comandos(), self.t)
        if b and not (img.valvula_aberta and img.agua_ok):
            self.violacoes_r02 += 1
        self.planta.bomba_cmd, self.planta.valvula_cmd = b, v
        self.planta.passo(C.PERIODO_SCAN)
        self.t = round(self.t + C.PERIODO_SCAN, 3)
        if self.ctrl.etapa != self.etapas[-1]:
            self.etapas.append(self.ctrl.etapa)

    def pulso(self, acao: str):
        self.passo(Comandos(**{acao: True}))
        self.passo()

    def rodar(self, segundos: float, ate=None) -> float:
        fim = self.t + segundos
        while self.t < fim - 1e-9:
            self.passo()
            if ate and ate(self):
                break
        return self.t

    def caminho(self) -> str:
        return " → ".join(C.NOMES_ETAPA[e].split()[0] for e in self.etapas)


@pytest.fixture
def sim():
    return Sim()


@pytest.fixture
def evidencia(request):
    def registrar(teste, condicao, entradas, esperado, observado):
        EVIDENCIAS.append(dict(id=teste, nome=request.node.name, condicao=condicao,
                               entradas=entradas, esperado=esperado, observado=observado,
                               resultado="?"))
    return registrar


@pytest.hookimpl(hookwrapper=True)
def pytest_runtest_makereport(item, call):
    rep = (yield).get_result()
    if rep.when == "call":
        for ev in EVIDENCIAS:
            if ev["nome"] == item.name and ev["resultado"] == "?":
                ev["resultado"] = "Aprovado" if rep.passed else "Reprovado"


def pytest_sessionfinish(session, exitstatus):
    if not EVIDENCIAS:
        return
    from datetime import datetime
    linhas = ["# Evidências dos testes T01–T09", "",
              f"Gerado automaticamente por `pytest` em {datetime.now():%d/%m/%Y %H:%M}.", "",
              "| Teste | Condição inicial | Entradas / ações | Resultado esperado | Observado | Status |",
              "|---|---|---|---|---|---|"]
    for e in sorted(EVIDENCIAS, key=lambda x: x["id"]):
        linhas.append("| {id} | {condicao} | {entradas} | {esperado} | {observado} | {resultado} |".format(**e))
    (RAIZ / "evidencias" / "testes.md").write_text("\n".join(linhas) + "\n", encoding="utf-8")
