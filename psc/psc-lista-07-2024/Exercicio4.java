import java.util.ArrayList;
import java.util.Scanner;

public class Exercicio4 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Double> vendas = new ArrayList<>();
        
        System.out.println("Informe as vendas dos vendedores (digite -1 para encerrar):");
        double venda = input.nextDouble();
        while (venda != -1) {
            vendas.add(venda);
            venda = input.nextDouble();
        }

        int[] faixasSalario = new int[9];

        for (double vendaSemana : vendas) {
            double salario = 200 + (0.09 * vendaSemana);
            if (salario >= 200 && salario < 300) {
                faixasSalario[0]++;
            } else if (salario >= 300 && salario < 400) {
                faixasSalario[1]++;
            } else if (salario >= 400 && salario < 500) {
                faixasSalario[2]++;
            } else if (salario >= 500 && salario < 600) {
                faixasSalario[3]++;
            } else if (salario >= 600 && salario < 700) {
                faixasSalario[4]++;
            } else if (salario >= 700 && salario < 800) {
                faixasSalario[5]++;
            } else if (salario >= 800 && salario < 900) {
                faixasSalario[6]++;
            } else if (salario >= 900 && salario < 1000) {
                faixasSalario[7]++;
            } else {
                faixasSalario[8]++;
            }
        }

        System.out.println("Número de vendedores em cada faixa salarial:");
        for (int i = 0; i < faixasSalario.length; i++) {
            if (i < 8) {
                System.out.println("$" + (200 + i * 100) + " - $" + (299 + i * 100) + ": " + faixasSalario[i]);
            } else {
                System.out.println("$" + (1000 + i * 100) + " em diante: " + faixasSalario[i]);
            }
        }

        input.close();
    }
}