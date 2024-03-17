/* organização mundial da saúde recomenda que sejam ingeridos 35 ml de água por dia, 
para cada quilograma de peso. Crie um algoritmo que recebe o peso de uma pessoa e informe 
a quantidade recomendada de água em litros. */

import java.util.Scanner;

    public class Exercicio10 {

    public static void main(String[] args) {
    Scanner dadosScanner= new Scanner(System.in);

    double peso, quilograma,totalLitros, totalMl;
    
    System.out.println("Digite seu peso: ");
        peso= dadosScanner.nextDouble();
        
        quilograma= 1000;
        totalMl= 35*(peso*quilograma);
        totalLitros= totalMl*1000;
        
    System.out.println("Você deve ingerir o total de: " +totalLitros+" litros de água");

    dadosScanner.close();
    }
}