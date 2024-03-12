import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double valorGasolina, ValorEtanol, valorToComper, percentGasolina;
        double percent = 70;

        System.out.print("Escreva o valor da gasolina: ");
        valorGasolina = scanner.nextDouble();

        System.out.print("Escreva o valor do etanol: ");
        ValorEtanol = scanner.nextDouble();

        percentGasolina = (percent / 100) * valorGasolina;
        valorToComper = valorGasolina - percentGasolina;
        
        if (ValorEtanol < valorToComper) {
            System.out.println("Com baso nos valores da gasolina e etanol COMPENSA comprar etanol.");
            scanner.close();
            return;
        }

        System.out.println("Com baso nos valores da gasolina e etanol NÃO compensa comprar etanol.");
        scanner.close();
        return;
    }
}