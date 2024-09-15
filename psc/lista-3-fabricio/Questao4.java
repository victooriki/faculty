/* Corrigindo questão 4 */

import java.util.Scanner;

/* faltou o public static */
public class Questao4 {
    public static void main(String[] args) {
        Scanner dadosScanner = new Scanner(System.in);
        double salario;
        double taxaAumento1 = 1.1;
        double taxaAumento2 = 1.2;
        double taxaAumento3 = 1.3;
        double taxa1, taxa2, taxa3;

        System.out.print("Por favor, informe o valor do salário atual: ");
        salario = dadosScanner.nextDouble();

        taxa1 = salario * taxaAumento1;
        taxa2 = salario * taxaAumento2;
        taxa3 = salario * taxaAumento3;

        if (salario <= 1000) {
            /* salario *= taxaAumento3(ERRADOO) */
            System.out.println("Seu salário foi para: " + taxa3);
            System.out.printf("Novo Salário: R$ %.2f\n\n", taxa3);
        } else if (salario <= 2000) {
            /* salario *= taxaAumento2; */
            System.out.println("Seu salário foi para: " + taxa2);
            System.out.printf("Novo Salário: R$ %.2f\n\n", taxa2);
        } else if (salario <= 3000) {
            /* salario *= taxaAumento1; */
            System.out.println("Seu salário foi para: " + taxa1);
            System.out.printf("Novo Salário: R$ %.2f\n\n", taxa1);
        } else if (salario <= 4000) {
            salario = (salario * taxaAumento1) + 200;
            System.out.printf("Novo Salário: R$ %.2f\n\n", salario);
        }

        dadosScanner.close();
    }
}