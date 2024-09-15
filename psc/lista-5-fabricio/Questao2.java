/*  Foi feita uma pesquisa sobre a audiência de canal de TV em várias casas de uma cidade, em determinado dia.
    Para cada casa consultada foi fornecido o número do canal (4, 5, 7, 12) e o número de pessoas que estavam assistindo aquele canal.
    Se a televisão estivesse desligada, nada era anotado, ou seja, essa casa não entrava na pesquisa. Faça um programa que:
    leia um número indeterminado de dados (número do canal e número de pessoas que estavam assistindo); e
    calcule e mostre a porcentagem de audiência de cada canal.
    Para encerrar a entrada de dados, digite o número do canal ZERO. */

import java.util.Scanner;

public class Questao2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int[] audiencia = new int[4];
        int totalPessoas = 0;
        
        while (true) {
            System.out.print("Digite o número do canal (ou 0 para encerrar): ");
            int canal = input.nextInt();
            
            if (canal == 0) {
                break;
            }
            
            if (canal >= 1 && canal <= 4) {
                System.out.print("Digite o número de pessoas assistindo: ");
                int pessoas = input.nextInt();
                
                audiencia[canal - 1] += pessoas;
                totalPessoas += pessoas;
            } else {
                System.out.println("Canal inválido. Tente novamente.");
            }
        }
        
        System.out.println("Total de pessoas assistindo aos canais: " + totalPessoas);
        for (int i = 0; i < audiencia.length; i++) {
            double porcentagem = (double) audiencia[i] / totalPessoas * 100;
            System.out.println("Porcentagem de audiência do canal " + (i + 1) + ": " + porcentagem + "%");
            System.out.println("Total de pessoas assistindo ao canal " + (i + 1) + ": " + audiencia[i]);
        }
        
        input.close();
    }
}