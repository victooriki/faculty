/*Você está trabalhando em uma startup voltada para educação, e ficou
responsável por criar a lógica de uma das atividades. Seu programa deve
receber o valor dos três lados de um triângulo, e informar se ele é equilátero,
isósceles ou escaleno. Restrição: Em um triângulo, o comprimento de um
lado é sempre menor do que a soma dos outros dois! */

import java.util.Scanner;

public class Questao8 {

    public static void main(String[] args) {
        Scanner dadosScanner = new Scanner(System.in);
        double x, y, z;

        System.out.println("Digite o comprimento do primeiro lado: ");
        x = dadosScanner.nextDouble();

        System.out.println("Digite o comprimento do segundo lado: ");
        y = dadosScanner.nextDouble();

        System.out.println("Digite o comprimento do terceiro lado: ");
        z = dadosScanner.nextDouble();

        if (x == 0 || y == 0 || z == 0) {
            System.out.println("Os lados fornecidos não formam um triângulo válido.");
            dadosScanner.close();
            return;
        }

        if (x == y && x == z) {
            System.out.println("O triângulo é equilátero.");
        } else if (x == y || x == z || y == z) {
            System.out.println("O triângulo é isósceles.");
        } else {
            System.out.println("O triângulo é escaleno.");
        }
        
        dadosScanner.close();
    }
}
