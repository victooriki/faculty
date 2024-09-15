// 2. Uma dúvida que sempre paira na cabeça dos motoristas é: Abastecer com etanol ou 
// gasolina? Especialistas indicam que o consumo do carro aumenta em torno de trinta por cento,
// quando abastecido com etanol, logo, só vale a pena abastecer com ele se o valor estiver
// abaixo de setenta por cento do valor da gasolina. Para auxiliar motoristas a realizar este
// cálculo, crie um algoritmo que receba o preço dos dois combustíveis, e informe qual deve ser
// a escolha, com base no custo.

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