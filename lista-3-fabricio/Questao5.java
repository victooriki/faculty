/* Escreva um programa em Java para determinar se o indivíduo está com um peso favorável.
 Essa situação é determinada através do IMC (Índice de Massa Corpórea), que é definida pela equação:
  IMC = pesoaltura2. A situação do peso é determinada pela tabela abaixo: 
 */

import java.util.Scanner;

public class Questao5 {
    public static void main(String[] args) {
        Scanner dadosScanner = new Scanner(System.in);
        int peso;
        Double altura, imc;
        
        System.out.print("Por favor, siga o exemplo peso(60) e altura(1,68): ");

        System.out.print("Por favor, informe o seu peso: ");
        peso = dadosScanner.nextInt();

        System.out.print("Por favor, informe a sua altura: ");
        altura = dadosScanner.nextDouble();

        imc = peso / (altura * altura);

        if (imc < 20) {
            System.out.println("Abaixo do peso: Seu IMC é " + imc);
        } else if (imc < 25) {
            System.out.println("Peso normal: Seu IMC é " + imc);
        } else if (imc < 30) {
            System.out.println("Sobrepeso: Seu IMC é " + imc);
        } else if (imc < 40) {
            System.out.println("Obeso: Seu IMC é " + imc);
        } else if (imc > 40) {
            System.out.println("Obeso Mórbido: Seu IMC é " + imc);
        }

        dadosScanner.close();
    }
}