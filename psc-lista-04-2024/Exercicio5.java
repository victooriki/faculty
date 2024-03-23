/*Altere o programa anterior permitindo ao usuário informar as populações e as
taxas de crescimento iniciais. Valide a entrada e permita repetir a operação */

import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args) {
        Scanner dadosScanner= new Scanner(System.in);
        double paisUm, paisDois;

        System.out.println("Digite a população do país A: ");
        paisUm = dadosScanner.nextDouble();

        System.out.println("Digite a população do país B: ");
        paisDois = dadosScanner.nextDouble();

        System.out.println(" Vamos calcular os anos necessários para que a população A ultrapasse ou iguale a B");
    
        int ano = 0;
        for (double index = paisUm * 0.03; index < paisDois * 0.015; index++) {
            ano++;

            System.out.print(ano + ", ");
        }

        dadosScanner.close();
    }
}