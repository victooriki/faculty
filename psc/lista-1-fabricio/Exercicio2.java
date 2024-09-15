import java.util.Scanner;
import java.lang.Math;

public class Exercicio2 {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int firstNum, secondNum, result;

        System.out.print("Escreva o primeiro número: ");
        firstNum = scanner.nextInt();

        System.out.print("Escreva o segundo número: ");
        secondNum = scanner.nextInt();

        result = firstNum % secondNum;

        System.out.print(String.format("O resto da divisão de %s por %s é: %s", firstNum, secondNum, Math.round(result)));
        scanner.close();
    }
}