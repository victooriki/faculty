/*Faça um programa que leia um número indeterminado de valores e mostre o valor lido,
 seu quadrado, seu cubo e sua raiz quadrada. Finalize a entrada com valor negativo ou zero*/

 import java.util.Scanner;

 public class Questao2 {
 
    public static void main(String[] args) {
        Scanner dadosScanner = new Scanner(System.in);

        double quadrado, cubo, raiz;
        int numero = 1;

        while (numero > 0) {
            System.out.println("Digite o seu número, para sair digite um número negativo ou zero: ");
            numero = dadosScanner.nextInt();

            quadrado = Math.pow(numero, 2);
            cubo = Math.pow(numero, 3);
            raiz = Math.sqrt(numero);

            System.out.println(" O seu número é : "+ numero);
            System.out.println(" O seu quadrado é : "+ quadrado);
            System.out.println(" O seu cubo é : "+ cubo);
            System.out.println(" A sua raiz é : "+ raiz);
        } 

        
        dadosScanner.close();
    }
 }