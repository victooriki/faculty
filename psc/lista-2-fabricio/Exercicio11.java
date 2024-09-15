/*Muitas pessoas têm dúvida sobre qual o gasto com energia de determinados equipamentos que possuem. 
Crie um algoritmo que receba a potência do equipamento em Watts, a quantidade de horas que ele fica 
ligado por dia, e o valor do KW/h. O cálculo é feito multiplicando a potência pela quantidade de horas,
depois, dividindo por mil. Ao final, apresente a quantidade de KWh consumidos pelo equipamento e qual
será o valor pago por eles. */

import java.util.Scanner;

    public class Exercicio11 {

    public static void main(String[] args) {
    Scanner dadosScanner= new Scanner(System.in);

    double watts,km,horas,totalConsumo,valorPago;

    System.out.println("Vamos calcular o gasto da sua energia!");
    System.out.println("Informe a potência do seu equipamento em Watts:");
     watts= dadosScanner.nextDouble();

    System.out.println("Informe a quantidade de horas na qual o equipamento fica ligado: ");
     horas= dadosScanner.nextDouble();

    System.out.println("Qual o valor do KW/h: ");
     km = dadosScanner.nextDouble();

     totalConsumo = (watts * horas) / 1000;
     valorPago = totalConsumo * km;

    System.out.println("Quantidade de kWh consumidos pelo equipamento: " + totalConsumo);
    System.out.println("Valor a ser pago pelos kWh consumidos: R$ " + valorPago);

    dadosScanner.close();
    }
    
}



    