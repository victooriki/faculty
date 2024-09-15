import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1, num2, num3, resultado;

        System.out.print("Digite o primeiro número: ");
        num1 = scanner.nextInt();

        System.out.print("Digite o segundo número: ");
        num2 = scanner.nextInt();

        System.out.print("Digite o terceiro número: ");
        num3 = scanner.nextInt();

        resultado = soma(num1, num2, num3);

        System.out.println("A soma dos três números é: " + resultado);

        scanner.close();
    }
    public static int soma(int num1, int num2, int num3) {
        int soma = num1 + num2 + num3;
        
        return soma;
    }
}
