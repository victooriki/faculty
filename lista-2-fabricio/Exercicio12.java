/*Pesquise o valor aproximado de dias por mês e ajuste o programa anterior para exibir 
o custo mensal em energia elétrica com o equipamento inserido */

import java.util.Scanner;

public class Exercicio12 {

    public static void main(String[] args) {
        
        Scanner dadosScanner= new Scanner(System.in);

        double watts,km,horas,totalConsumo,valorPago;

    System.out.println("Vamos calcular o gasto da sua energia no mês!");
    System.out.println("Informe a potência do seu equipamento em Watts:");
     watts= dadosScanner.nextDouble();

    System.out.println("Informe a quantidade de horas na qual o equipamento fica ligado: ");
     horas= dadosScanner.nextDouble();

    System.out.println("Qual o valor do KW/h: ");
     km = dadosScanner.nextDouble();

     totalConsumo = (watts * horas) / 1000;
     valorPago = ((totalConsumo * km)*31);

    System.out.println("Quantidade de kWh consumidos pelo equipamento: " + totalConsumo);
    System.out.println("Valor a ser pago por mês consumido: R$ " + valorPago);

        dadosScanner.close();
    }
}