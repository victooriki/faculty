/*Crie um programa que calcule a área, o perímetro e a diagonal de um retângulo. 
O resultado deve aparecer de maneira organizada e clara para a pessoa utilizadora.*/

import java.util.Scanner;

public class Exercicio5 {

    public static void main(String[] args) {
        Scanner dadoScanner= new Scanner(System.in);

        double base,altura,area,perimetro,diagonal,diagonalResultado;
        
        System.out.println("Vamos calcular a área de um retângulo!Assim descobriremos o perímetro e sua diagonal");
        System.out.println("Digite o valor da base: ");
        base=dadoScanner.nextDouble();
        System.out.println("Digite o valor da altura: ");
        altura=dadoScanner.nextDouble();

        area= base*altura;
        perimetro= 2 *(base + altura);
        diagonal= ((altura*altura) + (base*base));
        diagonalResultado=Math.sqrt(diagonal);

        System.out.println("A área do retângulo é: " + area);
        System.out.println("O perímetro do retângulo é: " + perimetro);
        System.out.println("A diagonal do retângulo é: " + diagonalResultado);

        dadoScanner.close();
    }
}