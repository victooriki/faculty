import java.util.Scanner;
import java.util.ArrayList;

public class Exercicio3 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Double> notas = new ArrayList<>();
        
        System.out.println("Digite as notas (digite -1 para encerrar):");
        double nota = input.nextDouble();
        while (nota != -1) {
            notas.add(nota);
            nota = input.nextDouble();
        }

        System.out.println("Quantidade de valores lidos: " + notas.size());
        
        System.out.println("Valores informados:");
        for (Double n : notas) {
            System.out.print(n + " ");
        }
        System.out.println();

        System.out.println("Valores informados em ordem inversa:");
        for (int i = notas.size() - 1; i >= 0; i--) {
            System.out.println(notas.get(i));
        }

        double soma = 0;
        for (Double n : notas) {
            soma += n;
        }
        System.out.println("Soma dos valores: " + soma);

        double media = soma / notas.size();
        System.out.println("Média dos valores: " + media);

        int acimaDaMedia = 0;
        for (Double n : notas) {
            if (n > media) {
                acimaDaMedia++;
            }
        }
        System.out.println("Quantidade de valores acima da média: " + acimaDaMedia);

        int abaixoDeSete = 0;
        for (Double n : notas) {
            if (n < 7) {
                abaixoDeSete++;
            }
        }
        System.out.println("Quantidade de valores abaixo de sete: " + abaixoDeSete);

        System.out.println("Programa encerrado.");
        input.close();
    }
}
