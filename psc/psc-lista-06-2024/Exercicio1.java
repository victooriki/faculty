import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] numeros = new int[5];

        System.out.println("Digite 5 números:");

        for (int x = 0; x < 5; x++) {
            try {
                numeros[x] = input.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                input.nextLine();
                x--;
                continue;
            }
        }

        System.out.println("Valores armazenados no vetor: ");
        for (int x = 0; x < 5; x++) {
            System.out.println("Posição do vetor: " + x + " | Valor armazenado: " + numeros[x]);
        }

        input.close();
    }
}