/*Supondo que a população de um país A seja da ordem de 80000 habitantes
com uma taxa anual de crescimento de 3% e que a população de B seja 200000
habitantes com uma taxa de crescimento de 1.5%
Faça um programa que calcule e escreva o número de anos necessários para
que a população do país A ultrapasse ou iguale a população do país B,
mantidas as taxas de crescimento. */

import java.util.Scanner;

public class Exercicio4 {
    public static void main(String[] args) {
        Scanner dadosScanner= new Scanner(System.in);
        double paisUm = 80000;
        double paisDois = 200000;

        System.out.println(" Vamos calcular os anos necessários para que a população A ultrapasse ou iguale a B");
    
        int ano = 0;
        for (double index = paisUm * 0.03; index < paisDois * 0.015; index++) {
            ano++;

            System.out.print(ano + ", ");
        }

        dadosScanner.close();
    }
}
