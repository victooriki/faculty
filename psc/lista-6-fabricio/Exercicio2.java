import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[] vetor1 = new int[10];
        int[] vetor2 = new int[10];
        int[] vetorIntercalado = new int[20];

        System.out.println("Preenchendo o primeiro vetor:");
        preencherVetor(input, vetor1);
        
        System.out.println("Preenchendo o segundo vetor:");
        preencherVetor(input, vetor2);

        intercalarVetores(vetor1, vetor2, vetorIntercalado);

        System.out.println("Vetor resultante da intercalação:");
        exibirVetor(vetorIntercalado);

        input.close();
    }

    public static void preencherVetor(Scanner input, int[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Digite o número " + (i + 1) + ": ");
            vetor[i] = input.nextInt();
        }
    }

    public static void intercalarVetores(int[] vetor1, int[] vetor2, int[] vetorIntercalado) {
        int indice1 = 0;
        int indice2 = 0;
        
        for (int i = 0; i < vetorIntercalado.length; i++) {
            if (i % 2 == 0) {
                vetorIntercalado[i] = vetor1[indice1];
                indice1++;
            } else {
                vetorIntercalado[i] = vetor2[indice2];
                indice2++;
            }
        }
    }

    public static void exibirVetor(int[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println();
    }
}
