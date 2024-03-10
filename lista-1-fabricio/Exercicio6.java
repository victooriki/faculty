import java.util.Scanner;

public class Exercicio6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int idade, result;

        System.out.print("Escreva sua idade: ");
        idade = scanner.nextInt();

        result = idade * 365;

        System.out.print(String.format("Você já viveu %s dias", result));
        scanner.close();
    }
}