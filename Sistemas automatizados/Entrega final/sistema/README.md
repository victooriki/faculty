# Irrigação automatizada de um canteiro — Entrega final (A3)

UNA Contagem · Sistemas Automatizados (UC 11210155) · 2026/2 · Prof. Francisco Deijangle
Squad: Victor Augusto Iki Teixeira e Marcos Júnior

Continuação do pré-projeto da Etapa 1 (`../../Primeira entrega`). O sistema implementa a
sequência GRAFCET E0–E5/EF, a escala 4–20 mA do SMT100, os intertravamentos R01–R07 e
a comunicação Modbus TCP entre CLP e planta virtual, com supervisão web.

## Arquitetura

```
 Supervisão web (HTTP :8080)  ──comandos──▶  CLP virtual (cliente Modbus TCP)
        ▲  estado, alarmes, tendência             │  lê DI/IR, escreve coils/HR a cada 100 ms
        │                                         ▼
        └──────── injeção de falhas ───────  Planta virtual (servidor Modbus TCP :5020)
```

| Arquivo | Papel |
|---|---|
| `config.py` | Parâmetros, tempos, escala 4–20 mA e mapa Modbus |
| `controlador.py` | Lógica do CLP: memória-imagem, GRAFCET, alarmes, indicadores |
| `planta.py` | Modelo da planta (umidade, válvula NF, bomba, fluxo, boia) e falhas injetáveis |
| `sistema.py` | Servidor Modbus da planta, CLP cliente, supervisão HTTP |
| `web/index.html` | Tela de supervisão (sinótico, GRAFCET, permissivos, E/S, tendência, eventos, modo de teste) |
| `openplc/irrigacao.st` | Mesmo algoritmo em Structured Text para o OpenPLC (**não compilado aqui — validar no OpenPLC Editor**) |
| `tests/` | Testes T01–T09 do enunciado |
| `evidencias/` | Tabela de testes gerada pelo pytest e capturas da supervisão |

## Mapa Modbus (planta = servidor, unidade 1, porta 5020)

| Tipo | Endereço | Sinal | Sentido |
|---|---|---|---|
| Discrete input | 0 | DI_AGUA_OK | planta → CLP |
| Discrete input | 1 | DI_VALVULA_ABERTA | planta → CLP |
| Discrete input | 2 | DI_FLUXO_OK | planta → CLP |
| Input register | 0 | AI_UMIDADE (µA, 4000–20000) | planta → CLP |
| Input register | 1 | contador de vida da planta | planta → CLP |
| Coil | 0 | DO_BOMBA | CLP → planta |
| Coil | 1 | DO_VALVULA | CLP → planta |
| Holding register | 0 | contador de vida do CLP | CLP → planta |
| Holding register | 1 / 2 / 3 | ETAPA / ALARME_ID / UMIDADE×10 | CLP → planta |

Contadores de vida: sem mudança por 3 s → o CLP falha com alarme 10, e a planta, se é o CLP que
parou, desliga bomba e válvula (modo seguro).

## Como executar

```bash
pip install -r requirements.txt
python sistema.py           # abre planta + CLP + supervisão
# navegador: http://localhost:8080
```

Para usar o OpenPLC como CLP no lugar do controlador Python: `python sistema.py --so-planta`
e cadastre a planta como *Slave Device* (endereços no cabeçalho de `openplc/irrigacao.st`).

## Testes

```bash
python -m pytest -v         # ~12 s; atualiza evidencias/testes.md
```

| Teste | O que verifica |
|---|---|
| T01 | Ciclo normal E0→E5 e ciclo dispensado |
| T02 | Escala 4–20 mA (0/30/40/50/100 %) |
| T03 | Permissivos: sem água, bomba só com válvula aberta |
| T04 | Etapa inativa não avança; conclusão registrada uma vez |
| T05 | Memória-imagem imutável durante o scan |
| T06 | Sinal anormal (0, 3,5 e 22 mA) e reset condicionado |
| T07 | Tempos excedidos: válvula, fluxo na partida, perda de fluxo, meta em 30 s, retorno em E4 |
| T08 | Reset não reinicia; Parar cancela sem retomar |
| T09 | Modbus TCP real: comando até a planta, dados congelados, reset, watchdog da planta |

## Alarmes

| ID | Causa |
|---|---|
| 1 | Sinal fora de 4–20 mA |
| 2 | Falta de água durante o ciclo |
| 3 | Válvula não abriu em 3 s |
| 4 | Sem fluxo em 2 s após ligar a bomba |
| 5 | Perda de fluxo durante a irrigação |
| 6 | Meta de 40 % não atingida em 30 s |
| 7 | Perda do permissivo de válvula aberta |
| 8 | Válvula/fluxo não retornaram ao repouso em 3 s |
| 9 | Umidade abaixo de 30 % após estabilizar |
| 10 | Comunicação desatualizada (> 3 s) |

## Limites

Planta com dinâmica simplificada e acelerada (+1 %/s irrigando, −0,05 %/s em repouso); não
representa calibração agronômica nem comprova economia de água. O volume mostrado é nominal
(12 L/min), não medido.
