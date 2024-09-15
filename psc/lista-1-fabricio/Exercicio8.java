import java.util.Scanner;

public class Exercicio8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int metros, centimetros;

        System.out.print("Escreva um valor em metros: ");
        metros = scanner.nextInt();

        centimetros = metros * 100;

        System.out.print(String.format("A conversão de %s metros em centímetros é %s", metros, centimetros));
        scanner.close();
    }
}