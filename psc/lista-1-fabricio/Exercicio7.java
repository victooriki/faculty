import java.util.Scanner;

public class Exercicio7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double salario, result;

        System.out.print("Escreva um sálario: ");
        salario = scanner.nextDouble();

        result = salario * 1.07;

        System.out.println("O sálario reajustado em 7% é R$"+ result);
        scanner.close();
    }
}