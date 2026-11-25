(* =====================================================================
   Irrigação automatizada de um canteiro — A3 Sistemas Automatizados
   Programa equivalente ao controlador.py, em Structured Text (IEC 61131-3)
   para OpenPLC Runtime. NÃO foi compilado no ambiente de desenvolvimento:
   importar no OpenPLC Editor, compilar e validar antes de usar.

   Endereçamento ao cadastrar a planta como "Slave Device" (Generic TCP,
   IP 127.0.0.1, porta 5020, ID 1) no OpenPLC:
     Discrete Inputs  start 0 size 3  -> %IX100.0 .. %IX100.2
     Input Registers  start 0 size 2  -> %IW100 .. %IW101
     Coils            start 0 size 2  -> %QX100.0 .. %QX100.1
     Holding Regs (w) start 0 size 4  -> %QW100 .. %QW103
   Comandos da supervisão: coils 0..2 do próprio OpenPLC (%QX0.0..%QX0.2).
   ===================================================================== *)
PROGRAM Irrigacao
  VAR
    DI_AGUA_OK        AT %IX100.0 : BOOL;
    DI_VALVULA_ABERTA AT %IX100.1 : BOOL;
    DI_FLUXO_OK       AT %IX100.2 : BOOL;
    AI_UMIDADE_uA     AT %IW100   : UINT;
    HB_PLANTA         AT %IW101   : UINT;
    DO_BOMBA          AT %QX100.0 : BOOL;
    DO_VALVULA        AT %QX100.1 : BOOL;
    HB_CLP            AT %QW100   : UINT;
    HR_ETAPA          AT %QW101   : UINT;
    HR_ALARME         AT %QW102   : UINT;
    HR_UMIDADE_x10    AT %QW103   : UINT;
    UI_INICIAR        AT %QX0.0   : BOOL;
    UI_PARAR          AT %QX0.1   : BOOL;
    UI_RESET          AT %QX0.2   : BOOL;
  END_VAR
  VAR
    (* memória-imagem *)
    agua, aberta, fluxo, com_ok, sinal_ok : BOOL;
    corrente_mA, umidade : REAL := 0.0;
    (* GRAFCET: 0..5 = E0..E5, 9 = EF *)
    etapa, alarme, ciclo : UINT := 0;
    irrigados, dispensados, cancelados : UINT := 0;
    fluxo_confirmado, irrigou : BOOL := FALSE;
    ini_ant, par_ant, res_ant, ini, par, res : BOOL := FALSE;
    hb_ant : UINT := 0;
    falhar : UINT := 0;
    (* temporizadores *)
    tCom, tE2, tE3fluxo, tE3max, tE4ret, tE4est : TON;
  END_VAR

  (* ---------- 1. memória-imagem e bordas ---------- *)
  agua := DI_AGUA_OK;  aberta := DI_VALVULA_ABERTA;  fluxo := DI_FLUXO_OK;
  corrente_mA := UINT_TO_REAL(AI_UMIDADE_uA) / 1000.0;
  sinal_ok := (corrente_mA >= 4.0) AND (corrente_mA <= 20.0);
  IF sinal_ok THEN
    umidade := (corrente_mA - 4.0) * 100.0 / 16.0;
  END_IF;
  tCom(IN := (HB_PLANTA = hb_ant), PT := T#3s);
  hb_ant := HB_PLANTA;
  com_ok := NOT tCom.Q;
  ini := UI_INICIAR AND NOT ini_ant;  ini_ant := UI_INICIAR;
  par := UI_PARAR   AND NOT par_ant;  par_ant := UI_PARAR;
  res := UI_RESET   AND NOT res_ant;  res_ant := UI_RESET;

  (* ---------- 2. temporizadores das etapas ---------- *)
  tE2(IN := (etapa = 2), PT := T#3s);
  tE3fluxo(IN := (etapa = 3), PT := T#2s);
  tE3max(IN := (etapa = 3), PT := T#30s);
  tE4ret(IN := (etapa = 4), PT := T#3s);
  tE4est(IN := (etapa = 4), PT := T#5s);

  (* ---------- 3. falhas globais (prioridade) ---------- *)
  falhar := 0;
  IF etapa <> 9 THEN
    IF NOT com_ok THEN falhar := 10;
    ELSIF NOT sinal_ok THEN falhar := 1;
    ELSIF (etapa >= 1) AND (etapa <= 3) AND NOT agua THEN falhar := 2;
    END_IF;
  END_IF;

  (* ---------- 4. parada R04 ---------- *)
  IF (falhar = 0) AND par AND (etapa >= 1) AND (etapa <= 4) THEN
    cancelados := cancelados + 1;
    etapa := 0;
  END_IF;

  (* ---------- 5. transições a partir da etapa ativa ---------- *)
  IF falhar = 0 THEN
    CASE etapa OF
      0:
        IF ini AND agua AND sinal_ok AND com_ok AND (alarme = 0)
           AND NOT aberta AND NOT fluxo THEN
          ciclo := ciclo + 1;  irrigou := FALSE;  fluxo_confirmado := FALSE;
          etapa := 1;
        END_IF;
      1:
        IF umidade < 30.0 THEN etapa := 2; ELSE etapa := 5; END_IF;
      2:
        IF aberta THEN etapa := 3;
        ELSIF tE2.Q THEN falhar := 3;
        END_IF;
      3:
        irrigou := TRUE;
        IF NOT aberta THEN falhar := 7;
        ELSIF fluxo THEN fluxo_confirmado := TRUE;
        ELSIF fluxo_confirmado THEN falhar := 5;
        ELSIF tE3fluxo.Q THEN falhar := 4;
        END_IF;
        IF falhar = 0 THEN
          IF fluxo_confirmado AND (umidade >= 40.0) THEN etapa := 4;
          ELSIF tE3max.Q THEN falhar := 6;
          END_IF;
        END_IF;
      4:
        IF tE4ret.Q AND (aberta OR fluxo) THEN falhar := 8;
        ELSIF tE4est.Q THEN
          IF umidade >= 30.0 THEN etapa := 5; ELSE falhar := 9; END_IF;
        END_IF;
      5:  (* registro único por ciclo: E5 dura um scan *)
        IF irrigou THEN irrigados := irrigados + 1;
        ELSE dispensados := dispensados + 1; END_IF;
        etapa := 0;
      9:
        IF res AND com_ok AND sinal_ok AND ((alarme <> 2) OR agua)
           AND NOT aberta AND NOT fluxo THEN
          alarme := 0;
          etapa := 0;
        END_IF;
    END_CASE;
  END_IF;

  IF falhar <> 0 THEN
    alarme := falhar;
    etapa := 9;
  END_IF;

  (* ---------- 6. ações contínuas e escrita ---------- *)
  DO_VALVULA := (etapa = 2) OR (etapa = 3);
  DO_BOMBA := (etapa = 3) AND agua AND aberta;      (* R02 *)
  HB_CLP := HB_CLP + 1;
  HR_ETAPA := etapa;
  HR_ALARME := alarme;
  HR_UMIDADE_x10 := REAL_TO_UINT(umidade * 10.0);
END_PROGRAM

CONFIGURATION Config0
  RESOURCE Res0 ON PLC
    TASK MainTask(INTERVAL := T#100ms, PRIORITY := 0);
    PROGRAM Inst0 WITH MainTask : Irrigacao;
  END_RESOURCE
END_CONFIGURATION
