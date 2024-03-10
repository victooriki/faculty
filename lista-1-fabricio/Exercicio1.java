import java.util.Scanner;
import java.lang.Math;

public class Exercicio1 {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        double firstNum, secondNum, result;

        System.out.print("Escreva o primeiro número: ");
        firstNum = input.nextDouble();

        System.out.print("Escreva o segundo número: ");
        secondNum = input.nextDouble();

        result = firstNum + secondNum;
        System.out.print(String.format("A soma dos números é %s", Math.round(result)));
        input.close();
    }
}