/* Escreva um programa que:
leia 100 fichas, onde cada ficha contém o número de matrícula e a nota de cada aluno de um determinado curso;
determine e imprima as duas maiores notas, juntamente com o número de matrícula do aluno que obteve cada uma delas;
assuma que não exista dois ou mais alunos com a mesma nota */

import java.util.Scanner;

public class Questao10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        final int fichas = 100;
        int[] matriculas = new int[fichas];
        double[] notas = new double[fichas];

        for (int i = 0; i < fichas; i++) {
            System.out.println("\nFicha " + (i + 1) + ":");
            matriculas[i] = i + 1;
            notas[i] = Math.random() * 10;
            System.out.println("Matrícula: " + matriculas[i]);
            System.out.println("Nota: " + notas[i]);
        }

        double maiorNota = Double.MIN_VALUE;
        int indiceMaiorNota = -1;
        double segundaMaiorNota = Double.MIN_VALUE;
        int indiceSegundaMaiorNota = -1;

        for (int i = 0; i < fichas; i++) {
            if (notas[i] > maiorNota) {
                segundaMaiorNota = maiorNota;
                indiceSegundaMaiorNota = indiceMaiorNota;
                maiorNota = notas[i];
                indiceMaiorNota = i;
            } else if (notas[i] > segundaMaiorNota) {
                segundaMaiorNota = notas[i];
                indiceSegundaMaiorNota = i;
            }
        }

        System.out.println("As duas maiores notas são:");
        System.out.println("1ª Maior Nota: " + maiorNota + ", Número de Matrícula: " + matriculas[indiceMaiorNota]);
        System.out.println("2ª Maior Nota: " + segundaMaiorNota + ", Número de Matrícula: " + matriculas[indiceSegundaMaiorNota]);

        input.close();
    }
}
