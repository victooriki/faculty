"""Testes T01–T08 do enunciado da A3 (lógica + planta em tempo simulado)."""
import dataclasses

import pytest

import config as C
import planta as planta_mod
from controlador import Comandos, Controlador, ImagemEntradas


def test_T01_operacao_normal(sim, evidencia):
    sim.pulso("iniciar")
    sim.rodar(60, ate=lambda s: s.ctrl.etapa == 4)
    u_meta = sim.ctrl.umidade
    sim.rodar(60, ate=lambda s: s.ctrl.ciclos_irrigados == 1 and s.ctrl.etapa == 0)
    c = sim.ctrl
    evidencia("T01", "E0, U = 25%, água OK, sem alarme", "Iniciar",
              "E0→E1→E2→E3→E4→E5→E0; meta de 40% atingida em E3; 1 ciclo irrigado",
              f"{sim.caminho()}; U = {u_meta:.1f}% ao sair de E3 e {c.umidade:.1f}% após estabilizar; "
              f"irrigados={c.ciclos_irrigados}; duração={c.ultima_duracao:.1f} s")
    assert u_meta >= C.META_IRRIGACAO
    assert sim.etapas == [0, 1, 2, 3, 4, 5, 0]
    assert c.ciclos_irrigados == 1 and c.alarme == 0
    assert c.umidade >= C.META_IRRIGACAO - 0.5     # pequena secagem em E4
    assert not c.bomba and not c.valvula
    assert sim.violacoes_r02 == 0


def test_T01b_ciclo_dispensado(sim, evidencia):
    sim.planta.umidade = 35.0
    sim.pulso("iniciar")
    sim.rodar(2)
    evidencia("T01", "E0, U = 35% (acima do limiar)", "Iniciar",
              "E1→E5 sem irrigar; ciclo dispensado",
              f"{sim.caminho()}; dispensados={sim.ctrl.ciclos_dispensados}; irrigados={sim.ctrl.ciclos_irrigados}")
    assert sim.etapas == [0, 1, 5, 0]
    assert sim.ctrl.ciclos_dispensados == 1 and sim.ctrl.ciclos_irrigados == 0


@pytest.mark.parametrize("ma,u", [(4.0, 0), (8.8, 30), (10.4, 40), (12.0, 50), (20.0, 100)])
def test_T02_escala_analogica(ma, u, evidencia):
    ctrl = Controlador()
    ctrl.scan(ImagemEntradas(True, False, False, ma, True), Comandos(), 0)
    evidencia("T02", "CLP em E0", f"I = {ma:.2f} mA", f"U = {u}% VWC",
              f"U = {ctrl.umidade:.2f}% VWC")
    assert ctrl.umidade == pytest.approx(u, abs=1e-9)


def test_T02b_transporte_em_microampere():
    """A corrente viaja em µA inteiros; 8,8 mA → 8800 µA → 30%."""
    p = planta_mod.Planta(umidade=30.0)
    assert p.corrente_ua() == 8800
    assert C.corrente_para_umidade(p.corrente_ua() / 1000) == pytest.approx(30.0)


def test_T03_permissivo_partida_sem_agua(sim, evidencia):
    sim.planta.falhas.sem_agua = True
    sim.passo()
    sim.pulso("iniciar")
    sim.rodar(3)
    ult = sim.ctrl.eventos[-1].texto
    evidencia("T03", "E0, U = 25%, reservatório vazio", "Iniciar",
              "Partida recusada; permanece em E0; saídas desligadas",
              f"Etapa {sim.ctrl.etapa}; bomba={sim.ctrl.bomba}; evento: “{ult}”")
    assert sim.ctrl.etapa == 0 and not sim.ctrl.bomba and not sim.ctrl.valvula
    assert "Água disponível" in ult


def test_T03b_perda_de_agua_durante_irrigacao(sim, evidencia):
    sim.pulso("iniciar")
    sim.rodar(10, ate=lambda s: s.ctrl.etapa == 3 and s.planta.fluxo)
    sim.planta.falhas.sem_agua = True
    sim.passo()     # a planta atualiza a boia
    sim.passo()     # o CLP lê a nova imagem
    evidencia("T03", "E3 irrigando com fluxo", "Boia indica sem água",
              "EF, alarme 2, bomba e válvula desligadas no mesmo scan",
              f"Etapa {sim.ctrl.etapa}; alarme {sim.ctrl.alarme}; bomba={sim.ctrl.bomba}; válvula={sim.ctrl.valvula}")
    assert sim.ctrl.etapa == C.ETAPA_EF and sim.ctrl.alarme == 2
    assert not sim.ctrl.bomba and not sim.ctrl.valvula
    assert sim.violacoes_r02 == 0


def test_T03c_bomba_so_liga_com_valvula_aberta(sim, evidencia):
    sim.pulso("iniciar")
    t_bomba = t_valv = None
    while sim.t < 10 and t_bomba is None:
        sim.passo()
        if sim.planta.valvula_aberta and t_valv is None:
            t_valv = sim.t
        if sim.ctrl.bomba:
            t_bomba = sim.t
    evidencia("T03", "Ciclo iniciado com U = 25%", "Válvula leva 0,8 s para abrir",
              "Bomba só liga após DI_VALVULA_ABERTA = 1 (R02)",
              f"válvula aberta em {t_valv:.1f} s; bomba ligada em {t_bomba:.1f} s; violações R02 = {sim.violacoes_r02}")
    assert t_bomba >= t_valv and sim.violacoes_r02 == 0


def test_T04_etapa_inativa(sim, evidencia):
    ctrl = sim.ctrl
    img = ImagemEntradas(agua_ok=True, valvula_aberta=True, fluxo_ok=True,
                         corrente_ma=C.umidade_para_corrente(45), comunicacao_ok=True)
    for i in range(30):                      # 3 s com sinais de E2/E3/E4 satisfeitos
        ctrl.scan(img, Comandos(), i * 0.1)
    evidencia("T04", "E0 sem Iniciar", "Válvula aberta, fluxo e U = 45% (condições de E2/E3/E4)",
              "Nenhuma transição; saídas desligadas; nenhum ciclo contado",
              f"Etapa {ctrl.etapa}; bomba={ctrl.bomba}; ciclos={ctrl.ciclo}")
    assert ctrl.etapa == 0 and not ctrl.bomba and not ctrl.valvula and ctrl.ciclo == 0


def test_T04b_conclusao_registrada_uma_vez(sim, evidencia):
    sim.planta.umidade = 35
    sim.pulso("iniciar")
    sim.rodar(5)
    n = sum(1 for e in sim.ctrl.eventos if e.tipo == "CONCLUIDO")
    evidencia("T04", "Ciclo dispensado", "Rodar 5 s após E5",
              "Conclusão registrada uma única vez (R07)", f"{n} registro(s) de conclusão")
    assert n == 1


def test_T05_memoria_imagem(evidencia):
    ctrl = Controlador()
    ctrl.etapa, ctrl._t_etapa = 2, 0.0
    img = ImagemEntradas(agua_ok=True, valvula_aberta=True, fluxo_ok=False,
                         corrente_ma=C.umidade_para_corrente(20), comunicacao_ok=True)
    bomba, valvula = ctrl.scan(img, Comandos(), 0.5)
    with pytest.raises(dataclasses.FrozenInstanceError):
        img.valvula_aberta = False           # entrada não pode mudar durante o scan
    evidencia("T05", "E2 aguardando abertura", "Imagem com DI_VALVULA_ABERTA = 1",
              "Transição E2→E3 e saídas calculadas com a MESMA imagem; imagem imutável no scan",
              f"Etapa {ctrl.etapa}; bomba={bomba}; válvula={valvula}; alteração da imagem bloqueada")
    assert ctrl.etapa == 3 and bomba and valvula


@pytest.mark.parametrize("ma", [0.0, 3.5, 22.0])
def test_T06_sinal_anormal(sim, evidencia, ma):
    sim.planta.falhas.corrente_forcada_ma = ma
    sim.rodar(0.5)
    a = sim.ctrl.alarme
    sim.pulso("reset")
    recusado = sim.ctrl.etapa == C.ETAPA_EF
    sim.planta.falhas.corrente_forcada_ma = None
    sim.rodar(0.3)
    sim.pulso("reset")
    evidencia("T06", "E0", f"Corrente forçada em {ma:.1f} mA",
              "EF com alarme 1; Reset recusado enquanto inválido; aceito após correção",
              f"alarme {a}; reset com causa ativa recusado={recusado}; após correção etapa {sim.ctrl.etapa}")
    assert a == 1 and recusado and sim.ctrl.etapa == 0 and sim.ctrl.alarme == 0


@pytest.mark.parametrize("falha,alarme,descr", [
    ("valvula_travada", 3, "válvula não abre"),
    ("linha_obstruida", 4, "sem fluxo na partida"),
    ("perder_fluxo", 5, "fluxo cai após 3 s"),
    ("ganho_baixo", 6, "umidade sobe devagar (meta não atingida em 30 s)"),
    ("sensor_preso", 8, "sensor de válvula não retorna em E4"),
])
def test_T07_tempo_excedido_e_supervisao(sim, evidencia, monkeypatch, falha, alarme, descr):
    f = sim.planta.falhas
    if falha == "valvula_travada":
        f.valvula_travada = True
    elif falha == "linha_obstruida":
        f.linha_obstruida = True
    elif falha == "perder_fluxo":
        f.perder_fluxo_em = 3.0
    elif falha == "ganho_baixo":
        monkeypatch.setattr(planta_mod, "GANHO_IRRIGACAO", 0.2)
    elif falha == "sensor_preso":
        f.sensor_valvula_preso = True
    sim.pulso("iniciar")
    t0 = sim.t
    sim.rodar(60, ate=lambda s: s.ctrl.etapa == C.ETAPA_EF)
    evidencia("T07", "E0, U = 25%", f"Iniciar com falha: {descr}",
              f"EF com alarme {alarme}; saídas desligadas",
              f"{sim.caminho()}; alarme {sim.ctrl.alarme} em {sim.t - t0:.1f} s; "
              f"bomba={sim.ctrl.bomba}; válvula={sim.ctrl.valvula}")
    assert sim.ctrl.etapa == C.ETAPA_EF and sim.ctrl.alarme == alarme
    assert not sim.ctrl.bomba and not sim.ctrl.valvula


def test_T08_reset_e_reinicio(sim, evidencia):
    sim.planta.falhas.linha_obstruida = True
    sim.pulso("iniciar")
    sim.rodar(10, ate=lambda s: s.ctrl.etapa == C.ETAPA_EF)
    sim.planta.falhas.linha_obstruida = False
    sim.rodar(2)                                   # válvula retorna ao repouso
    sim.passo(Comandos(reset=True))
    for _ in range(20):                            # Reset mantido pressionado
        sim.passo(Comandos(reset=True))
    etapa_pos_reset = sim.ctrl.etapa
    bomba_pos_reset = sim.ctrl.bomba
    sim.rodar(2)
    sem_inicio = sim.ctrl.etapa == 0 and sim.ctrl.ciclo == 1
    sim.pulso("iniciar")
    sim.rodar(60, ate=lambda s: s.ctrl.ciclos_irrigados == 1 and s.ctrl.etapa == 0)
    evidencia("T08", "EF (alarme 4) com causa corrigida", "Reset mantido 2 s; depois Iniciar",
              "Reset leva a E0 sem iniciar irrigação; novo ciclo só com novo Iniciar",
              f"após reset: etapa {etapa_pos_reset}, bomba={bomba_pos_reset}, sem reinício automático={sem_inicio}; "
              f"após Iniciar: ciclo {sim.ctrl.ciclo} irrigado")
    assert etapa_pos_reset == 0 and not bomba_pos_reset and sem_inicio
    assert sim.ctrl.ciclos_irrigados == 1 and sim.ctrl.ciclo == 2


def test_T08b_parar_cancela_sem_retomar(sim, evidencia):
    sim.pulso("iniciar")
    sim.rodar(10, ate=lambda s: s.ctrl.etapa == 3 and s.planta.fluxo)
    sim.pulso("parar")
    sim.rodar(3)
    evidencia("T08", "E3 irrigando", "Parar",
              "Saídas desligadas, cancelamento registrado, retorno a E0 sem retomar",
              f"etapa {sim.ctrl.etapa}; cancelados={sim.ctrl.ciclos_cancelados}; bomba={sim.ctrl.bomba}")
    assert sim.ctrl.etapa == 0 and sim.ctrl.ciclos_cancelados == 1 and not sim.ctrl.bomba
