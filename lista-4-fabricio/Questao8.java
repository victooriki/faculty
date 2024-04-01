/* Escreva um programa em Java que calcule o produto de A (número real) por B (número inteiro), ou seja, A*B, por intermédio de adições sucessivas. Tanto A quanto B devem ser fornecidos pela pessoa utilizadora do programa. */

import java.util.Scanner;

public class Questao8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double valorA, resultado;
        int valorB;

        System.out.print("Digite o valor de A onde é 1° fator da multiplicação (número real) : ");
        valorA = input.nextDouble();
        
        System.out.print("Digite o valor do 2° onde é fator da multiplicação (número inteiro): ");
        valorB = input.nextInt();
        
        resultado = multiplicacao(valorA, valorB);
        
        System.out.println("A multiplicação de " + valorA + " por " + valorB + " é: " + resultado);

        input.close();
    }
    
    public static double multiplicacao(double valA, int valB) {
        double resultado = 0;
        
        for (int i = 0; i < Math.abs(valB); i++) {
            resultado += valA;
        }
        
        return resultado;
    }
}
