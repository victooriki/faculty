import java.util.Scanner;

public class Exercicio4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int valorA, valorB, valorRegra;

        System.out.print("Digite um valor inteiro: ");
        valorA = scanner.nextInt();

        System.out.print("Digite outro valor inteiro: ");
        valorB = scanner.nextInt();

        valorRegra = ( valorA / valorB ) * 100;

        System.out.print(String.format("A porcentam de %s por %s na regra de três é %s porcento",
        valorA, valorB, valorRegra));

        scanner.close();
    }
}