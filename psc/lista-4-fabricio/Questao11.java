/* Na usina de Angra dos Reis, os técnicos analisam a perda de massa de um material radioativo. Sabendo-se que este perde 25% de sua massa a cada 30 segundos. Criar um programa que calcule iterativamente e imprima o tempo necessário para que a massa deste material se torne menor que 0,10 grama. O programa pode calcular o tempo para várias massas. */

import java.util.Scanner;


public class Questao11 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int opcao;
        do {
            System.out.print("Digite a massa inicial em gramas: ");
            double massaInicial = input.nextDouble();

            double massaLimite = 0.10;
            double percentualPerda = 0.25;
            int tempo = 0;

            while (massaInicial >= massaLimite) {
                massaInicial *= (1 - percentualPerda);
                
                tempo += 30;
            }

            System.out.println("Tempo necessário para a massa se tornar menor que 0,10 gramas: " + tempo + " segundos.");

            System.out.println("Deseja fazer a comparação com outra massa?\n1. Sim\n2. Não (Sair)");
            opcao = input.nextInt();

        } while (opcao == 1);

        System.out.println("Programa encerrado.");
        input.close();
    }
}
