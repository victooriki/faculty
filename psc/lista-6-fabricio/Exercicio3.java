import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[] vetor = new int[8];
        int[] vetorPositivo = new int[8];
        int[] vetorNegativo = new int[8];
        
        int indicePositivo = 0;
        int indiceNegativo = 0;

        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Insira o número " + (i + 1) + ": ");
            vetor[i] = input.nextInt();
        }

        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] >= 0) {
                vetorPositivo[indicePositivo] = vetor[i];
                indicePositivo++;
            } else {
                vetorNegativo[indiceNegativo] = vetor[i];
                indiceNegativo++;
            }
        }

        System.out.println("\nNúmeros positivos:");
        for (int i = 0; i < indicePositivo; i++) {
            System.out.print(vetorPositivo[i] + " ");
        }

        System.out.println("\n\nNúmeros negativos:");
        for (int i = 0; i < indiceNegativo; i++) {
            System.out.print(vetorNegativo[i] + " ");
        }

        input.close();
    }
}
