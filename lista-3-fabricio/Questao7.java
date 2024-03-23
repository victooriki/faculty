/*Escreva um programa em Java para ler: a descrição do produto (nome), a
quantidade adquirida e o preço unitário. Calcular e escrever o total (total =
quantidade adquirida * preço unitário), o desconto e o total a pagar (total a
pagar = total - desconto), sabendo-se que:
- Se quantidade <= 5, o desconto será de 2%
- Se quantidade > 5 e quantidade <=10, o desconto será de 3%
- Se quantidade > 10 e quantidade <30, o desconto será de 5%
- Se quantidade >= 30 o desconto será de 10% */

import java.util.Scanner;

public class Questao7 {

    public static void main(String[] args) {

        Scanner dadosScanner = new Scanner(System.in);

        String nome;
        int quantidade;
        float preçoUnd;
        double desconto = 0;
        double total, totalComDesconto;

        System.out.println("Digite o nome do produto: ");
        nome = dadosScanner.nextLine();

        System.out.println("Digite a quantidade: ");
        quantidade = dadosScanner.nextInt();

        System.out.println("Digite o valor unitário: ");
        preçoUnd = dadosScanner.nextFloat();

        total = quantidade * preçoUnd;

        if (quantidade <= 5) {
            desconto = total * 0.02;
        } else if (quantidade > 5) {
            desconto = total * 0.03;
        } else if (quantidade <= 10) {
            desconto = total * 0.03;
        } else if (quantidade > 10) {
            desconto = total * 0.05;
        } else if (quantidade < 30) {
            desconto = total * 0.05;
        } else if (quantidade >= 30) {
            desconto = total * 0.10;
        }

        totalComDesconto = total - desconto;

        System.out.println("O total da sua compra  do produto " + nome + " foi " + total + ", com o desconto foi para " + totalComDesconto);

        dadosScanner.close();
    }
}
