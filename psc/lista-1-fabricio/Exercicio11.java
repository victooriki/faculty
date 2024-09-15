import java.util.Scanner;
import java.text.DecimalFormat;

public class Exercicio11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double notaUm, notaDois, notaTres, mediaNotas;

        System.out.print("Escreva a primeira nota: ");
        notaUm = scanner.nextDouble();

        System.out.print("Escreva a segunda nota: ");
        notaDois = scanner.nextDouble();

        System.out.print("Escreva a terceira nota: ");
        notaTres = scanner.nextDouble();

        mediaNotas = (notaUm + notaDois + notaTres) / 3;

        System.out.printf("A média das notas é "+ arredondar(mediaNotas));
        scanner.close();
    }

    private static double arredondar(double valor) {
        DecimalFormat formato = new DecimalFormat("0.0");
        double numeroArredondado = Double.parseDouble(formato.format(valor));

        return numeroArredondado;
    }
}