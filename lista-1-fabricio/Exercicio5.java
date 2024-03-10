import java.util.Scanner;
import java.text.DecimalFormat;

public class Exercicio5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double valor, result;

        System.out.println("Escreva o valor em dólar que deseja converter em real:");
        valor = scanner.nextDouble();

        result = conversor(valor);

        System.out.print(String.format("O resultado da conversão é R$%s", arredondar(result)));
        scanner.close();
    }

    private static double conversor(double valor) {
        double valorReal = 4.95;

        return valor * valorReal;
    }

    private static double arredondar(double valor) {
        DecimalFormat formato = new DecimalFormat("0.00");
        double numeroArredondado = Double.parseDouble(formato.format(valor));

        return numeroArredondado;
    }
}