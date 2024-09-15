/*Escreva um programa em Java para ler as notas da 1ª, 2ª e  3ª avaliações de um aluno.
 Calcular a média aritmética simples e escrever uma mensagem que diga se o aluno foi ou não aprovado
  (considerar que média igual ou maior que 7 o aluno é aprovado). Escrever também a média calculada.
 */

import java.util.Scanner;

public class Questao2 {
    public static void main(String[] args) {
        Scanner dadosScanner = new Scanner(System.in);
        double primeira, segunda, terceira, resultado;

        System.out.println("Digite a sua primeira nota: ");
        primeira = dadosScanner.nextDouble();

        System.out.println("Digite a sua segunda nota: ");
        segunda = dadosScanner.nextDouble();

        System.out.println("Digite a sua terceira nota: ");
        terceira = dadosScanner.nextDouble();

        resultado = ((primeira + segunda + terceira) / 3);

        if (resultado >= 7) {
            System.out.println(" Você foi aprovado! sua média foi " + resultado);
            dadosScanner.close();
            return;
        }

        System.out.println(" Você foi reprovado! sua média foi " + resultado);
        dadosScanner.close();
    }

}
