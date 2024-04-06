/*Faça um programa que receba várias idades, calcule e mostre a média das idades digitadas. 
Finalize digitando idade igual a zero.*/

import java.util.Scanner;

public class Questao1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int tamanhoMaximo = 20;
        int[] idades = new int[tamanhoMaximo];
        
        int contador = 0;
        for (contador = 0; contador < tamanhoMaximo; contador++) {
            System.out.print("Digite uma idade (ou 0 para finalizar): ");
            int idade = input.nextInt();

            if (idade == 0) {
                input.close();
                break;
            }

            idades[contador] = idade;
        }

        int soma = 0;
        for (int i = 0; i < tamanhoMaximo && idades[i] != 0; i++) {
            soma += idades[i];
        }
        
        double media = (double) soma / contador;

        System.out.println("A média das idades digitadas é: " + media);
        input.close();
    }
}