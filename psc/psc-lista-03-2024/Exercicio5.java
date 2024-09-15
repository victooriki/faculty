import java.text.DecimalFormat;
import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double numeroUm, numeroDois, resultado;
        char operador;

        System.out.println("Digite o primeiro número: ");
        numeroUm = scanner.nextDouble();
        
        System.out.println("Digite o símbolo da operação (ex: +, -, *, / ou ^): ");
        operador = scanner.next().charAt(0);

        System.out.println("Digite o segundo número: ");
        numeroDois = scanner.nextDouble();


        switch (operador) {
            case '+':
                resultado = numeroUm + numeroDois;
                break;
            case '-':
                resultado = numeroUm - numeroDois;
                break;
            case '*':
                resultado = numeroUm * numeroDois;
                break;
            case '/':
                resultado = numeroUm / numeroDois;
                break;
            case '^':
                resultado = Math.pow(numeroUm, numeroDois);
                break;
            default:
                System.out.println("Símbolo de operação inválido. Tente novamento com um símbolo válido.");
                scanner.close();
                return;
        }

        System.out.println("Resultado da operação: " + arredondar(resultado));

        scanner.close();
    }

    private static double arredondar(double valor) {
        DecimalFormat formato = new DecimalFormat("0.0");
        double numeroArredondado = Double.parseDouble(formato.format(valor));

        return numeroArredondado;
    }
}