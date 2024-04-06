/*  Faça um programa que receba dez idades, pesos e alturas, calcule e mostre: 
    a média das idades das dez pessoas;
    a quantidade de pessoas com peso superior a 90 kg e altura inferior a 1,50 metro; e 
    a porcentagem de pessoas com idade entre 10 e 30 anos entre as pessoas que medem mais de 1,90 m.  */

import java.util.Scanner;

public class Questao4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int totalPessoas = 10;
        int[] idades = new int[totalPessoas];
        double[] pesos = new double[totalPessoas];
        double[] alturas = new double[totalPessoas];
        
        for (int i = 0; i < totalPessoas; i++) {
            System.out.println("Pessoa " + (i + 1));
            System.out.print("Idade: ");
            idades[i] = input.nextInt();
            System.out.print("Peso (kg): ");
            pesos[i] = input.nextDouble();
            System.out.print("Altura (m): ");
            alturas[i] = input.nextDouble();
        }
        
        double somaIdades = 0;
        for (int idade : idades) {
            somaIdades += idade;
        }
        double mediaIdades = somaIdades / totalPessoas;
        System.out.println("Média das idades das dez pessoas: " + mediaIdades);
        
        int contadorPesoAltura = 0;
        for (int i = 0; i < totalPessoas; i++) {
            if (pesos[i] > 90 && alturas[i] < 1.50) {
                contadorPesoAltura++;
            }
        }
        System.out.println("Quantidade de pessoas com peso superior a 90 kg e altura inferior a 1,50 m: " + contadorPesoAltura);
        
        int contadorIdadeAltura = 0;
        for (int i = 0; i < totalPessoas; i++) {
            if (idades[i] >= 10 && idades[i] <= 30 && alturas[i] > 1.90) {
                contadorIdadeAltura++;
            }
        }
        double porcentagemIdadeAltura = (double) contadorIdadeAltura / totalPessoas * 100;
        System.out.println("Porcentagem de pessoas com idade entre 10 e 30 anos entre as pessoas que medem mais de 1,90 m: " + porcentagemIdadeAltura + "%");
        
        input.close();
    }
}