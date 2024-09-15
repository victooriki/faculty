/*  Uma empresa fez uma pesquisa de mercado para saber se as pessoas gostaram ou não de um novo produto lançado.
    Para isso, forneceu o sexo do entrevistado e sua resposta (S — sim; ou N — não).
    Sabe-se que foram entrevistadas dez pessoas. Faça um programa que calcule e mostre:
    o número de pessoas que responderam sim;
    o número de pessoas que responderam não;
    o número de mulheres que responderam sim; e
    a percentagem de homens que responderam não, entre todos os homens analisados  */

import java.util.Scanner;

public class Questao5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int totalEntrevistados = 10;
        char[] sexo = new char[totalEntrevistados];
        char[] resposta = new char[totalEntrevistados];
        
        for (int i = 0; i < totalEntrevistados; i++) {
            System.out.println("Entrevistado " + (i + 1));
            System.out.print("Sexo (M — masculino; F — feminino): ");
            sexo[i] = input.next().charAt(0);
            System.out.print("Resposta (S — sim; N — não): ");
            resposta[i] = input.next().charAt(0);
        }
        
        int sim = 0;
        int nao = 0;
        int mulheresSim = 0;
        int homens = 0;
        int homensNao = 0;
        
        for (int i = 0; i < totalEntrevistados; i++) {
            if (resposta[i] == 'S' || resposta[i] == 's') {
                sim++;
                if (sexo[i] == 'F' || sexo[i] == 'f') {
                    mulheresSim++;
                }
            } else if (resposta[i] == 'N' || resposta[i] == 'n') {
                nao++;
                if (sexo[i] == 'M' || sexo[i] == 'm') {
                    homens++;
                    homensNao++;
                }
            }
        }
        
        double percentagemHomensNao = homensNao != 0 && homens != 0 ? (double) homensNao / homens * 100 : 0;
        
        System.out.println("Número de pessoas que responderam sim é: " + sim);
        System.out.println("Número de pessoas que responderam não é: " + nao);
        System.out.println("Número de mulheres que responderam sim é: " + mulheresSim);
        System.out.println("Percentagem de homens que responderam não é: " + percentagemHomensNao + "%");
        
        input.close();
    }
}