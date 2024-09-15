/*Escreva um programa que seja capaz de realizar a soma de duas frações, não é necessário simplificar.
 As entradas serão o numerador e, o denominador da primeira fração, seguidos do numerador e,
  o denominador da segunda fração. O resultado também deverá ser exibido em formato de fração. e.g: ⅗ */

  
  import java.util.Scanner;

  public class Exercicio13 {
  
    public static void main(String[] args) {
        
        Scanner dadosScanner= new Scanner(System.in);

        int numeradorPrimeira,denominadorPrimeira,numeradorSegunda, denominadorSegunda, numerador, denominador;
        System.out.println("Digite o numerador da primeira fração: ");
        numeradorPrimeira= dadosScanner.nextInt();

        System.out.println("Digite o denominador da primeira fração: ");
        denominadorPrimeira= dadosScanner.nextInt();


        System.out.println("Digite o numerador da segunda fração: ");
        numeradorSegunda= dadosScanner.nextInt();


        System.out.println("Digite o numerador da segunda fração: ");
        denominadorSegunda= dadosScanner.nextInt();

        numerador= (numeradorPrimeira+ numeradorSegunda);
        denominador= (denominadorPrimeira+denominadorSegunda);


        System.out.println("O resultado foi: "+ numerador+ "/"+denominador);

        dadosScanner.close();

    }

  }