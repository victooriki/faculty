import java.util.Scanner;
import java.lang.Math;
import java.text.DecimalFormat;

public class Exercicio9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double raio, area;

        System.out.print("Escreva o raio do círculo: ");
        raio = scanner.nextDouble();

        area = Math.PI * Math.pow(raio, 2);

        System.out.print(String.format("A área do círculo com raio %s é %s", raio, arredondar(area)));
        scanner.close();
    }

    private static double arredondar(double valor) {
        DecimalFormat formato = new DecimalFormat("0.000");
        double numeroArredondado = Double.parseDouble(formato.format(valor));

        return numeroArredondado;
    }
}