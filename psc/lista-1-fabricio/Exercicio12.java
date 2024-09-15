import java.util.Scanner;

public class Exercicio12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num, antecessor, sucessor;

        System.out.print("Escreva um número: ");
        num = scanner.nextInt();

        antecessor = num - 1;
        sucessor = num + 1;

        System.out.print(String.format("Antecessor: %s \nSucessor: %s", num, antecessor, sucessor));
        scanner.close();
    }
}