/*Crie um programa que lê vários números inteiros e positivos 
e imprima o produto dos números ímpares e a soma dos números pares.*/

import java.util.Scanner;

public class Questao1 {

  public static void main(String[] args) {

    Scanner dadosScanner = new Scanner(System.in);
    int numero;
    int soma = 0;

    System.out.println("Os números ímpares são:");

    for (numero = 0; numero < 100; numero++) {

      if (numero % 2 != 0) {
        System.out.print(numero + ", ");
      }

      if (numero % 2 == 0) {
        soma = soma + numero;
      }

      dadosScanner.close();
    }
    
    System.out.println("\nA soma dos números pares foi: " + soma);
    dadosScanner.close();
  }
}