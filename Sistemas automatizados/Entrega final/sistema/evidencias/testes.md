# Evidências dos testes T01–T09

Gerado automaticamente por `pytest` em 25/11/2026 19:34.

| Teste | Condição inicial | Entradas / ações | Resultado esperado | Observado | Status |
|---|---|---|---|---|---|
| T01 | E0, U = 25%, água OK, sem alarme | Iniciar | E0→E1→E2→E3→E4→E5→E0; meta de 40% atingida em E3; 1 ciclo irrigado | E0 → E1 → E2 → E3 → E4 → E5 → E0; U = 40.0% ao sair de E3 e 39.8% após estabilizar; irrigados=1; duração=21.5 s | Aprovado |
| T01 | E0, U = 35% (acima do limiar) | Iniciar | E1→E5 sem irrigar; ciclo dispensado | E0 → E1 → E5 → E0; dispensados=1; irrigados=0 | Aprovado |
| T02 | CLP em E0 | I = 4.00 mA | U = 0% VWC | U = 0.00% VWC | Aprovado |
| T02 | CLP em E0 | I = 8.80 mA | U = 30% VWC | U = 30.00% VWC | Aprovado |
| T02 | CLP em E0 | I = 10.40 mA | U = 40% VWC | U = 40.00% VWC | Aprovado |
| T02 | CLP em E0 | I = 12.00 mA | U = 50% VWC | U = 50.00% VWC | Aprovado |
| T02 | CLP em E0 | I = 20.00 mA | U = 100% VWC | U = 100.00% VWC | Aprovado |
| T03 | E0, U = 25%, reservatório vazio | Iniciar | Partida recusada; permanece em E0; saídas desligadas | Etapa 0; bomba=False; evento: “Iniciar recusado: Água disponível” | Aprovado |
| T03 | E3 irrigando com fluxo | Boia indica sem água | EF, alarme 2, bomba e válvula desligadas no mesmo scan | Etapa 9; alarme 2; bomba=False; válvula=False | Aprovado |
| T03 | Ciclo iniciado com U = 25% | Válvula leva 0,8 s para abrir | Bomba só liga após DI_VALVULA_ABERTA = 1 (R02) | válvula aberta em 0.9 s; bomba ligada em 1.0 s; violações R02 = 0 | Aprovado |
| T04 | E0 sem Iniciar | Válvula aberta, fluxo e U = 45% (condições de E2/E3/E4) | Nenhuma transição; saídas desligadas; nenhum ciclo contado | Etapa 0; bomba=False; ciclos=0 | Aprovado |
| T04 | Ciclo dispensado | Rodar 5 s após E5 | Conclusão registrada uma única vez (R07) | 1 registro(s) de conclusão | Aprovado |
| T05 | E2 aguardando abertura | Imagem com DI_VALVULA_ABERTA = 1 | Transição E2→E3 e saídas calculadas com a MESMA imagem; imagem imutável no scan | Etapa 3; bomba=True; válvula=True; alteração da imagem bloqueada | Aprovado |
| T06 | E0 | Corrente forçada em 0.0 mA | EF com alarme 1; Reset recusado enquanto inválido; aceito após correção | alarme 1; reset com causa ativa recusado=True; após correção etapa 0 | Aprovado |
| T06 | E0 | Corrente forçada em 3.5 mA | EF com alarme 1; Reset recusado enquanto inválido; aceito após correção | alarme 1; reset com causa ativa recusado=True; após correção etapa 0 | Aprovado |
| T06 | E0 | Corrente forçada em 22.0 mA | EF com alarme 1; Reset recusado enquanto inválido; aceito após correção | alarme 1; reset com causa ativa recusado=True; após correção etapa 0 | Aprovado |
| T07 | E0, U = 25% | Iniciar com falha: válvula não abre | EF com alarme 3; saídas desligadas | E0 → E1 → E2 → EF; alarme 3 em 3.0 s; bomba=False; válvula=False | Aprovado |
| T07 | E0, U = 25% | Iniciar com falha: sem fluxo na partida | EF com alarme 4; saídas desligadas | E0 → E1 → E2 → E3 → EF; alarme 4 em 2.8 s; bomba=False; válvula=False | Aprovado |
| T07 | E0, U = 25% | Iniciar com falha: fluxo cai após 3 s | EF com alarme 5; saídas desligadas | E0 → E1 → E2 → E3 → EF; alarme 5 em 3.8 s; bomba=False; válvula=False | Aprovado |
| T07 | E0, U = 25% | Iniciar com falha: umidade sobe devagar (meta não atingida em 30 s) | EF com alarme 6; saídas desligadas | E0 → E1 → E2 → E3 → EF; alarme 6 em 30.8 s; bomba=False; válvula=False | Aprovado |
| T07 | E0, U = 25% | Iniciar com falha: sensor de válvula não retorna em E4 | EF com alarme 8; saídas desligadas | E0 → E1 → E2 → E3 → E4 → EF; alarme 8 em 19.3 s; bomba=False; válvula=False | Aprovado |
| T08 | EF (alarme 4) com causa corrigida | Reset mantido 2 s; depois Iniciar | Reset leva a E0 sem iniciar irrigação; novo ciclo só com novo Iniciar | após reset: etapa 0, bomba=False, sem reinício automático=True; após Iniciar: ciclo 2 irrigado | Aprovado |
| T08 | E3 irrigando | Parar | Saídas desligadas, cancelamento registrado, retorno a E0 sem retomar | etapa 0; cancelados=1; bomba=False | Aprovado |
| T09 | Planta (servidor) e CLP (cliente) via Modbus TCP 127.0.0.1 | Iniciar; congelar dados da planta; Reset; parar o CLP com bomba ligada | Comando chega à planta; falha 10 em ~3 s com saídas desligadas; Reset obrigatório; planta desliga atuadores sem CLP | bomba e fluxo na planta 1.5 s após Iniciar; alarme 10 em 3.0 s; DO_BOMBA=False; bomba na planta=False; após restabelecer, permaneceu em EF até Reset; voltou a E0 sem irrigar; CLP parado com bomba ligada → planta em modo seguro em 3.1 s; bomba=False, válvula=False; 86 leituras Modbus bem-sucedidas | Aprovado |
