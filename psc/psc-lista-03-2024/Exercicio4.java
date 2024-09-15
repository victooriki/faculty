import java.text.DecimalFormat;
import java.util.Scanner;

public class Exercicio4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOperacao;
        double raio, pi;
        double perimetro, area, volume;

        System.out.println("Digite o indicador de operação: \n" +
                "1 = Calcular o perímetro do círculo.\n" +
                "2 = Calcular a área do círculo.\n" +
                "3 = Calcular o volume da esfera. ");
        numOperacao = scanner.nextInt();

        System.out.print("Digite o raio do círculo ou da esfera: ");
        raio = scanner.nextDouble();

        pi = Math.PI;

        switch (numOperacao) {
            case 1:
                perimetro = 2 * pi * raio;
                System.out.println("O perímetro do círculo é: " + arredondar(perimetro));
                break;
            case 2:
                area = pi * raio * raio;
                System.out.println("A área do círculo é: " + arredondar(area));
                break;
            case 3:
                volume = (4.0 / 3.0) * pi * Math.pow(raio, 3);
                System.out.println("O volume da esfera é: " + arredondar(volume));
                break;
            default:
                System.out.println("Código de operação inválido. Tente novamente com um código válido.");
        }

        scanner.close();
    }

    private static double arredondar(double valor) {
        DecimalFormat formato = new DecimalFormat("0.00");
        double numeroArredondado = Double.parseDouble(formato.format(valor));

        return numeroArredondado;
    }
}