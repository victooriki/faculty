// 3. Escreva um programa que calcule o teorema de Pitágoras,
// representado pela expressão: c =√(a^2+b^2 )

import java.text.DecimalFormat;
import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double valorA, valorB, valorPitagoras;

        System.out.print("Digite o valor de A: ");
        valorA = scanner.nextDouble();

        System.out.print("Digite o valor de B: ");
        valorB = scanner.nextDouble();

        valorPitagoras = Math.sqrt(Math.pow(valorA, 2) + Math.pow(valorB, 2));

        System.out.print(String.format("O resultado de %s e %s no Teorema de Pitagoras é %s",
        valorA, valorB, arredondar(valorPitagoras)));

        scanner.close();
    }

    private static double arredondar(double valor) {
        DecimalFormat formato = new DecimalFormat("0.000");
        double numeroArredondado = Double.parseDouble(formato.format(valor));

        return numeroArredondado;
    }
}