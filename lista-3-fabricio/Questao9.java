/*Crie um programa capaz de ler a idade de uma pessoa e imprimir sua
classificação eleitoral. Entrada: QUAL A IDADE DA PESSOA?: 17
Saída: ELEITOR FACULTATIVO */

import java.util.Scanner;

public class Questao9 {

  public static void main(String[] args) {
    Scanner dadosScanner = new Scanner(System.in);

    System.out.println(" Vamos descobrir sua classificação eleitoral!");
    
    System.out.println(" Digite sua idade: ");
    int idade = dadosScanner.nextInt();

    if (idade < 16) {
      System.out.println("Não eleitor");

    } else if (idade == 16 || idade <= 17) {
      System.out.println("Eleitor facultativo");

    } else if (idade == 18 || idade <= 65) {
      System.out.println("Eleitor obrigatório");

    }
    if (idade >= 65) {
      System.out.println("Eleitor facultativo");

    }

    dadosScanner.close();

  }
}
