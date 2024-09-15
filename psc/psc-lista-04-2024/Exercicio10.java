import java.util.Scanner;

public class Exercicio10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numUm, numDois, troca;
        
        System.out.println("Digite o primeiro número: ");
        numUm = scanner.nextInt();

        System.out.println("Digite o segundo número: ");
        numDois = scanner.nextInt();

        troca = -1;

        if(numUm > numDois) {
            troca = numUm;
        }

        if(troca != -1) {
            for (int index = numDois + 1; index < troca; index++) {
                System.out.print(index + " ");
            }
        }

        for (int index = numUm + 1; index < numDois; index++) {
            System.out.print(index + " ");
        }
        
        scanner.close();
    }
}