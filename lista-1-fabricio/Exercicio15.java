import java.text.DecimalFormat;
import java.util.Scanner;

public class Exercicio15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double valorA, valorB, valorC, delta, raizUm, raizDois;

        System.out.print("Digite o valor de A: ");
        valorA = scanner.nextDouble();

        System.out.print("Digite o valor de B: ");
        valorB = scanner.nextDouble();

        System.out.print("Digite o valor de C: ");
        valorC = scanner.nextDouble();

        delta = valorB * valorB - 4 * valorA * valorC;

        if (delta < 0) {
            System.out.println("A equação não possui raízes reais.");
            scanner.close();
            return;
        }

        raizUm = (-valorB + Math.sqrt(delta)) / (2 * valorA);
        raizDois = (-valorB - Math.sqrt(delta)) / (2 * valorA);

        System.out.print(String.format("Raiz 1: %s\nRaiz 2: %s", arredondar(raizUm), arredondar(raizDois)));

        scanner.close();
    }

    private static double arredondar(double valor) {
        DecimalFormat formato = new DecimalFormat("0.000");
        double numeroArredondado = Double.parseDouble(formato.format(valor));

        return numeroArredondado;
    }
}