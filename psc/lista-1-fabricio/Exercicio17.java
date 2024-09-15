import java.text.DecimalFormat;
import java.util.Scanner;

public class Exercicio17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int qtdCanetas, valorNota;
        double valorTroco, valorCaneta;

        System.out.println("Escreva a quantidade de canetas compradas:");
        qtdCanetas = scanner.nextInt();

        System.out.println("Escreva o valor da nota usada para pagamento:");
        valorNota = scanner.nextInt();

        System.out.println("Escreva o valor do troco:");
        valorTroco = scanner.nextInt();

        valorCaneta = (valorNota - valorTroco) / qtdCanetas;

        System.out.printf("O valor de cada caneta foi R$" + arredondar(valorCaneta));
        
        scanner.close();
    }

        private static double arredondar(double valor) {
        DecimalFormat formato = new DecimalFormat("0.00");
        double numeroArredondado = Double.parseDouble(formato.format(valor));

        return numeroArredondado;
    }
}