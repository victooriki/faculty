/*  Foi feita uma estatística em n cidades brasileiras para coletar dados sobre acidentes de trânsito. Foram obtidos os seguintes dados:
    nome da cidade;
    número de veículos de passeio;
    número de acidentes de trânsito com vítimas.
    Deseja-se saber:
    qual é o maior e qual é o menor índice de acidentes de trânsito e a que cidades pertencem;
    qual é a média de veículos nas cinco cidades juntas  */

import java.util.Scanner;

public class Questao7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int numCidades = 5;
        String[] nomesCidades = new String[numCidades];
        int[] numVeiculos = new int[numCidades];
        int[] numAcidentes = new int[numCidades];

        for (int i = 0; i < numCidades; i++) {
            System.out.println("\nDados da cidade " + (i + 1) + ":");
            System.out.print("Nome da cidade: ");
            nomesCidades[i] = input.nextLine();

            System.out.print("Número de veículos de passeio: ");
            numVeiculos[i] = input.nextInt();

            System.out.print("Número de acidentes de trânsito com vítimas: ");
            numAcidentes[i] = input.nextInt();

            input.nextLine();
        }

        int maiorIndice = numAcidentes[0];
        int menorIndice = numAcidentes[0];
        String cidadeMaiorIndice = nomesCidades[0];
        String cidadeMenorIndice = nomesCidades[0];

        for (int i = 1; i < numCidades; i++) {
            if (numAcidentes[i] > maiorIndice) {
                maiorIndice = numAcidentes[i];
                cidadeMaiorIndice = nomesCidades[i];
            }

            if (numAcidentes[i] < menorIndice) {
                menorIndice = numAcidentes[i];
                cidadeMenorIndice = nomesCidades[i];
            }
        }

        int totalVeiculos = 0;
        for (int veiculos : numVeiculos) {
            totalVeiculos += veiculos;
        }
        double mediaVeiculos = (double) totalVeiculos / numCidades;

        System.out.println("\nCidade com maior índice de acidentes: " + cidadeMaiorIndice + " (" + maiorIndice + " acidentes)");
        System.out.println("Cidade com menor índice de acidentes: " + cidadeMenorIndice + " (" + menorIndice + " acidentes)");
        System.out.println("Média de veículos nas cidades: " + mediaVeiculos);

        input.close();
    }
}