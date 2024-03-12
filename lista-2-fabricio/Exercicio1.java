// 1. Crie um algoritmo que leia um valor de hora (HH:MM) e
// informe (calcule) quantos minutos se passaram desde o início do dia (00:00).

import java.util.Calendar;
import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int horaToMin, result, segundosArredondado;
        int hora, minuto, segundo;

        Calendar calendario = Calendar.getInstance();
		hora = calendario.get(Calendar.HOUR_OF_DAY);
		minuto = calendario.get(Calendar.MINUTE);
		segundo = calendario.get(Calendar.SECOND);

        horaToMin = hora * 60;
        segundosArredondado = segundo > 30 ? 1 : 0;
        result = horaToMin + minuto + segundosArredondado;

        System.out.println("O horário atual em minutos é "+result);
        scanner.close();
    }
}