import java.util.Scanner;

public class Exercicio8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double numUm, numDois, numTres, numQuatro, numCinco, soma, media;
        
        System.out.println("Digite o primeiro número: ");
        numUm = scanner.nextDouble();

        System.out.println("Digite o segundo número: ");
        numDois = scanner.nextDouble();

        System.out.println("Digite o terceiro número: ");
        numTres = scanner.nextDouble();

        System.out.println("Digite o quarto número: ");
        numQuatro = scanner.nextDouble();

        System.out.println("Digite o quinto número: ");
        numCinco = scanner.nextDouble();

        soma = numUm + numDois + numTres + numQuatro + numCinco;

        media = soma / 5;


        System.out.print(String.format("A soma dos números é %s e a média é %s", soma, media));
        scanner.close();
    }
}