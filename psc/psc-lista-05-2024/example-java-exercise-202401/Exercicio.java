import java.util.Scanner;

public class Exercicio {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        double[] temperatura = new double[12];
        double media = 0;

        for (int i = 0; i < 12; i++) {
            System.out.println("Insira a temperatura " + (i + 1) + " (entre 4 e 10): ");
            temperatura[i] = input.nextDouble();

            while (temperatura[i] < 4 || temperatura[i] > 10) {
                System.out.print("Valor Inválido! Digite um valor novamente: ");
                temperatura[i] = input.nextDouble();
            }

            media += temperatura[i];
        } 

        media /= temperatura.length;

        System.out.printf("A média das temperaturas é de: %.2f%n", media);

        input.close();
    }
}