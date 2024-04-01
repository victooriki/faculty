/* Faça um algoritmo que receba a idade, altura e peso de 10 pessoas. Calcule e mostre:
a) a quantidade de pessoas com idade superior a 50 anos;
b) a média das alturas das pessoas com idade entre 10 e 20 anos;
c) a porcentagem de pessoas com peso inferior a 40 quilos entre todas as pessoas
*/
import java.util.Scanner;

public class Questao3 {
 
    public static void main(String[] args) {
        Scanner dadosScanner = new Scanner(System.in);

        int pessoas = 0, idadeSuperior = 0, quantidadePessoas = 0, quantidadeInferior = 0;
        double somaAlturas = 0, mediaAlturas, porcentagemInferior;

        for (pessoas = 1; pessoas <= 3; pessoas++) {
            System.out.println("Digite sua idade:");
            int idade = dadosScanner.nextInt();
            System.out.println("Digite sua altura:");
            double altura = dadosScanner.nextDouble();
            System.out.println("Digite seu peso:");
            int peso = dadosScanner.nextInt();

            if (idade >= 50) {
                idadeSuperior++;
            }
            if (idade >= 10 && idade <= 20) {
                quantidadePessoas++;
                somaAlturas += altura;
            }
            if (peso < 40) {
                quantidadeInferior++;
            }

            dadosScanner.close();
        }

        mediaAlturas = quantidadePessoas > 0 ? somaAlturas / quantidadePessoas : 0;
        porcentagemInferior = (double) quantidadeInferior / 10 * 100;

        System.out.println("São superior ou igual a 50, " + idadeSuperior + " pessoas");
        System.out.println("A média das alturas das pessoas com idade entre 10 e 20 anos: " + mediaAlturas);
        System.out.println("A porcentagem de pessoas com peso inferior a 40 quilos entre todas as pessoas: " + porcentagemInferior);

        dadosScanner.close();
    }
}
