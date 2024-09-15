/*Crie um algoritmo que leia dois números e exiba o resultado da divisão entre eles.
 O algoritmo deverá seguir todas as regras da divisão de números reais. */

import java.util.Scanner;

public class Questao1 {

  public static void main(String[] args) {
    Scanner dadosScanner = new Scanner(System.in);
    int numeroUm, numeroDois, resultado;

    System.out.println("Vamos realizar a divisão de dois números reais!");
    System.out.println("Digite o primeiro número: ");
    numeroUm = dadosScanner.nextInt();

    System.out.println("Digite o segundo número: ");
    numeroDois = dadosScanner.nextInt();

    resultado = numeroUm / numeroDois;

    System.out.println("O resultado da divisão do número " + numeroUm + " pelo número " + numeroDois + " é igual " + resultado + " !");

    dadosScanner.close();
  }
}