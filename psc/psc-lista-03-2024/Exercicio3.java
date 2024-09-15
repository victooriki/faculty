import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double valorA, valorB, valorC, valorDelta, raiz, raizUm, raizDois;

        System.out.print("Digite o coeficiente a: ");
        valorA = scanner.nextDouble();

        System.out.print("Digite o coeficiente b: ");
        valorB = scanner.nextDouble();

        System.out.print("Digite o coeficiente c: ");
        valorC = scanner.nextDouble();

        valorDelta = valorB * valorB - 4 * valorA * valorC;

        if (valorA == 0 && valorB == 0 && valorC != 0) {
            System.out.println("Coeficientes informados incorretamente.");
            scanner.close();
            return;
        }
        
        if (valorA == 0 && valorB != 0) {
            System.out.println("Essa é uma equação de primeiro grau.");
            raiz = -valorC / valorB;
            System.out.println("A raiz real da equação é: " + raiz);
            scanner.close();
            return;
        }
        
        if (valorDelta < 0) {
            System.out.println("A equação não possui raízes reais.");
            scanner.close();
            return;
        }
        
        if (valorDelta == 0) {
            System.out.println("A equação possui duas raízes reais iguais.");
            raiz = -valorB / (2 * valorA);
            System.out.println("A raiz real da equação é: " + raiz);
            scanner.close();
            return;
        }

        System.out.println("Esta equação possui duas raízes reais diferentes.");
        raizUm = (-valorB + Math.sqrt(valorDelta)) / (2 * valorA);
        raizDois = (-valorB - Math.sqrt(valorDelta)) / (2 * valorA);
        System.out.println("As raízes reais da equação são: " + raizUm + " e " + raizDois);
        
        scanner.close();
    }
}