import java.util.Scanner;

public class Exercicio6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        final int numeroAlunos = 10;
        double[] medias = new double[numeroAlunos];
        int contadorAprovados = 0;

        System.out.println("Digite as quatro notas de cada aluno:");

        for (int i = 0; i < numeroAlunos; i++) {
            double soma = 0;

            for (int j = 0; j < 4; j++) {
                System.out.print(String.format("Nota %s do aluno %s: ", (j+1), (i+1)));
                double nota = input.nextDouble();
                soma += nota;
            }

            medias[i] = soma / 4;
        }

        for (int i = 0; i < numeroAlunos; i++) {
            if (medias[i] >= 7.0) {
                contadorAprovados++;
            }
        }

        System.out.println("Número de alunos com média maior ou igual a 7: " + contadorAprovados);

        input.close();
    }
}