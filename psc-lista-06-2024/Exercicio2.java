import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[] numeros = new double[10];

        System.out.println("Digite 10 números reais:");

        for (int x = 0; x < 10; x++) {
            try {
                numeros[x] = input.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                input.nextLine();
                x--;
                continue;
            }
        }

        System.out.println("Números na ordem inversa:");

        for (int i = 9; i >= 0; i--) {
            System.out.println(numeros[i]);
        }

        input.close();
    }
}