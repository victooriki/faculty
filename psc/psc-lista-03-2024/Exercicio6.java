import java.util.Random;
import java.util.Scanner;

public class Exercicio6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numeroUm, numeroDois, numMenor, numMaior, numSorteio;

        System.out.println("Digite o primeiro número: ");
        numeroUm = scanner.nextInt();

        System.out.println("Digite o segundo número: ");
        numeroDois = scanner.nextInt();

        numMenor = Math.min(numeroUm, numeroDois);
        numMaior = Math.max(numeroUm, numeroDois);
        numSorteio = numeroAleatorio(numMenor, numMaior);

        if (numSorteio % 2 == 0) {
            System.out.println("O número sorteado é " + numSorteio + " e par");
            scanner.close();
            return;
        }
        
        System.out.println("O número sorteado é " + numSorteio + " e ímpar");
        scanner.close();
    }

    public static int numeroAleatorio(int min, int max){
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
    
        return randomNum;
    }
}