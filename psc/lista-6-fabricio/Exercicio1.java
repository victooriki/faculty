import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] num = new int[9];
        
        for (int i = 0; i < num.length; i++) {
            System.out.print("Digite o número " + (i + 1) + ": ");
            num[i] = input.nextInt();
        }

        for (int i = 0; i < num.length; i++) {
            int divisao = 0;
            
            for (int j = 1; j <= num[i]; j++) {
                if (num[i] % j == 0) {
                    divisao++;
                }
            }

            if (divisao == 2) {
                System.out.println("O número " + num[i] + " é primo.");
                System.out.println("Sua posição é: " + i);
            }
        }
        
        input.close();
    }
}
