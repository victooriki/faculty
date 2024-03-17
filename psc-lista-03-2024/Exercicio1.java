import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double valorA, valorB, valorC, valorMaior, valorMenor, valorMedia;

        System.out.print("Digite o primeiro número: ");
        valorA = scanner.nextDouble();

        System.out.print("Digite o segundo número: ");
        valorB = scanner.nextDouble();

        System.out.print("Digite o terceiro número: ");
        valorC = scanner.nextDouble();

        valorMaior = getMaior(valorA, valorB, valorC);
        valorMenor = getMenor(valorA, valorB, valorC);
        valorMedia = (valorA + valorB + valorC) / 3;

        System.out.print(String.format("O maior número é %s\nO menor número é %s\nA média aritmética dos três números é %s",
        valorMaior, valorMenor, valorMedia));

        scanner.close();
    }

    public static double getMaior(double num1, double num2, double num3) {
        double maior = num1 > num2 ? (num1 > num3 ? num1 : num3) : (num2 > num3 ? num2 : num3);
        return maior;
    }

    public static double getMenor(double num1, double num2, double num3) {
        double maior = num1 < num2 ? (num1 < num3 ? num1 : num3) : (num2 < num3 ? num2 : num3);
        return maior;
    }
}