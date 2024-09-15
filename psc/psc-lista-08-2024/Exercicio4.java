import java.util.Scanner;

public class Exercicio4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num;
        String resultado;

        System.out.print("Digite um número: ");
        num = scanner.nextInt();

        resultado = isPositivo(num);

        if (resultado == "P") {
            System.out.println("O número é positivo.");
            scanner.close();
            return;
        }

        System.out.println("O número é negativo.");
        scanner.close();
    }

    public static String isPositivo(int num) {
        String result = num > 0 ? "P" : "N";
        return result;
    }
}
