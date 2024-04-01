/* Seja a seguinte série:
1, 4, 4, 2, 5, 5, 3, 6, 6, 4, 7, 7, …

Escreva um programa que seja capaz de gerar os N termos dessa série.
Esse número N deve ser lido do teclado. */

import java.util.Scanner;

public class Questao12 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o número de termos da série: ");
        int length = input.nextInt();

        int termo1 = 1;
        int termo2 = 4;
        int termo3 = 4;

        for (int i = 1; i <= length; i++) {
            if (i % 3 == 1) {
                System.out.print(termo1 + " ");
                termo1++;
            } else if (i % 3 == 2) {
                System.out.print(termo2 + " ");
                termo2++;
            } else {
                System.out.print(termo3 + " ");
                termo3++;
            }
        }

        input.close();
    }
}
