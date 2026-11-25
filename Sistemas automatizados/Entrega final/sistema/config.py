"""Parâmetros do projeto e mapa Modbus (Etapa 2 em diante).

Todos os valores são didáticos e foram herdados do pré-projeto (Etapa 1).
"""

# ---------------------------------------------------------------- Rede
MODBUS_HOST = "127.0.0.1"
MODBUS_PORTA = 5020          # porta > 1024 para não exigir privilégio de root
MODBUS_UNIDADE = 1
HTTP_PORTA = 8080            # supervisão web

# ------------------------------------------------------- Mapa Modbus (planta = servidor)
# Entradas discretas (FC02) — lidas pelo CLP
DI_AGUA_OK = 0
DI_VALVULA_ABERTA = 1
DI_FLUXO_OK = 2
# Registradores de entrada (FC04) — lidos pelo CLP
IR_UMIDADE_uA = 0            # corrente 4.000–20.000 µA (0 = sinal ausente)
IR_HB_PLANTA = 1             # contador de vida da planta
# Coils (FC05/FC15) — escritas pelo CLP
CO_BOMBA = 0
CO_VALVULA = 1
# Registradores holding (FC06/FC16) — escritos pelo CLP
HR_HB_CLP = 0                # contador de vida do CLP
HR_ETAPA = 1                 # 0..5 = E0..E5, 9 = EF
HR_ALARME = 2                # ALARME_ID retido
HR_UMIDADE_x10 = 3           # umidade calculada pelo CLP × 10

# ---------------------------------------------------------------- Tempos (s)
PERIODO_SCAN = 0.1           # ciclo de varredura do CLP (troca Modbus a cada scan)
PERIODO_PLANTA = 0.1         # passo de integração da planta
TIMEOUT_COMUNICACAO = 3.0    # sem atualização do contador → falha
T_ABERTURA_VALVULA = 3.0     # E2: confirmação de abertura
T_PARTIDA_FLUXO = 2.0        # E3: fluxo deve surgir após ligar a bomba
T_MAX_IRRIGACAO = 30.0       # E3: tempo máximo para atingir a meta
T_RETORNO = 3.0              # E4: aberta/fluxo devem zerar
T_ESTABILIZACAO = 5.0        # E4: espera antes de conferir a umidade

# ------------------------------------------------------------ Limites de umidade
LIMIAR_IRRIGAR = 30.0        # % VWC: abaixo disso, irrigar
META_IRRIGACAO = 40.0        # % VWC: encerra a irrigação

# --------------------------------------------------------- Escala analógica (AN006)
I_MIN_mA = 4.0
I_MAX_mA = 20.0
U_MAX_pct = 100.0


def corrente_para_umidade(i_ma: float) -> float:
    """U(%VWC) = (I − 4) × 100 / 16  —  relação linear da nota AN006."""
    return (i_ma - I_MIN_mA) * U_MAX_pct / (I_MAX_mA - I_MIN_mA)


def umidade_para_corrente(u_pct: float) -> float:
    return I_MIN_mA + u_pct * (I_MAX_mA - I_MIN_mA) / U_MAX_pct


def sinal_valido(i_ma: float) -> bool:
    return I_MIN_mA <= i_ma <= I_MAX_mA


# ----------------------------------------------------------------- Etapas e alarmes
ETAPA_EF = 9
NOMES_ETAPA = {
    0: "E0 Aguardar",
    1: "E1 Avaliar",
    2: "E2 Abrir válvula",
    3: "E3 Irrigar",
    4: "E4 Estabilizar",
    5: "E5 Concluir",
    ETAPA_EF: "EF Falha",
}

ALARMES = {
    0: "Sem alarme",
    1: "Sinal analógico inválido (fora de 4–20 mA)",
    2: "Falta de água durante o ciclo",
    3: "Válvula não confirmou abertura em 3 s",
    4: "Sem fluxo na partida da bomba (2 s)",
    5: "Perda de fluxo durante a irrigação",
    6: "Tempo excedido: meta não atingida em 30 s",
    7: "Perda do permissivo de válvula aberta",
    8: "Válvula/fluxo não retornaram ao repouso em 3 s",
    9: "Umidade não estabilizou acima de 30%",
    10: "Comunicação desatualizada (> 3 s)",
}
