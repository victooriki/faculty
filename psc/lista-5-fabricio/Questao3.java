/*  Faça um programa que apresente o menu de opções a seguir:
    Menu de opções:
    1. Média aritmética
    2. Média ponderada
    3. Sair
    Digite a opção desejada.
    Na opção 1: receber duas notas, calcular e mostrar a média aritmética.
    Na opção 2: receber três notas e seus respectivos pesos, calcular e mostrar média ponderada.
    Na opção 3: sair do programa.
    Verifique a possibilidade de opção inválida. Nesse caso, o programa deverá mostrar uma mensagem.  */

import java.util.Scanner;

public class Questao3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("Menu de opções:");
            System.out.println("1. Média aritmética");
            System.out.println("2. Média ponderada");
            System.out.println("3. Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = input.nextInt();
            
            switch (opcao) {
                case 1:
                    mediaAritimetica(input);
                    break;
                case 2:
                    mediaPonderada(input);
                    break;
                case 3:
                    System.out.println("Programa interrompido...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
            
            System.out.println();
        } while (opcao != 3);
        
        input.close();
    }
    
    public static void mediaAritimetica(Scanner input) {
        double[] notas = new double[2];
        
        for (int i = 0; i < notas.length; i++) {
            System.out.print("Digite a nota " + (i + 1) + ": ");
            notas[i] = input.nextDouble();
        }
        
        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        
        double media = soma / notas.length;
        System.out.println("A média aritmética é: " + media);
    }
    
    public static void mediaPonderada(Scanner input) {
        double[] notas = new double[3];
        double[] pesos = new double[3];
        
        for (int i = 0; i < notas.length; i++) {
            System.out.print("Digite a nota " + (i + 1) + ": ");
            notas[i] = input.nextDouble();
            
            System.out.print("Digite o peso da nota " + (i + 1) + ": ");
            pesos[i] = input.nextDouble();
        }
        
        double somaNotasPonderadas = 0;
        double somaPesos = 0;
        for (int i = 0; i < notas.length; i++) {
            somaNotasPonderadas += notas[i] * pesos[i];
            somaPesos += pesos[i];
        }
        
        double mediaPonderada = somaNotasPonderadas / somaPesos;
        System.out.println("A média ponderada é: " + mediaPonderada);
    }
}