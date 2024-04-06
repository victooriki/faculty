/*  Sendo H = 1 + 1/2 + 1/3 + 1/4 + ... + 1/N, fazer um algoritmo, utilizando o comando para ...
    faça, para gerar o número H.
    O número N é lido através de uma unidade de entrada qualquer uma única vez.  */

import java.util.Scanner;

public class Questao8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o valor de N sendo um número inteiro: ");
        int N = input.nextInt();

        double somaHarmonica = 0.0;

        StringBuilder contaStr = new StringBuilder("H = ");
        for (int i = 1; i <= N; i++) {
            somaHarmonica += 1.0 / i;
            if (i == 1) {
                contaStr.append("1");
            } else {
                contaStr.append(" + 1/").append(i);
            }
        }

        System.out.println(contaStr.toString() + " = " + somaHarmonica);

        input.close();
    }
}