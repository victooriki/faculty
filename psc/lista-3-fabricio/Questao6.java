/*Escreva um programa que peça ao usuário que insira um número de 1 a 4,
correspondendo a cada estação do ano (1 para Primavera, 2 para Verão, 3
para Outono, e 4 para Inverno) para identificar a estação escolhida e imprimir
uma mensagem característica daquela estação.
 */

import java.util.Scanner;

public class Questao6 {

    public static void main(String[] args) {
        Scanner dadosScanner = new Scanner(System.in);
        int numero;
        
        System.out.println(" Digite um número de  1 a 4: ");
        numero = dadosScanner.nextInt();

        if (numero == 1) {
            System.out.println("Sua estação é a Primavera");
            System.out.println("- Renascimento da natureza.\r\n" +
                    " - Florescimento das plantas e retorno da vida animal.\r\n" +
                    "- Temperaturas amenas e instáveis.\r\n" +
                    "- Dias mais longos e chuvas frequentes.");
        }
        if (numero == 2) {
            System.out.println(" Sua estação é o Verão");
            System.out.println("Estação mais quente do ano.\r\n" +
                    "Dias longos e noites curtas.\r\n" +
                    "Atividades ao ar livre e vida ativa.\r\n" +
                    "Plantas em pleno crescimento e colheitas abundantes.");
        }
        if (numero == 3) {
            System.out.println(" Sua estação é o Outono");
            System.out.println("Queda das folhas das árvores.\r\n" +
                    "Mudança de cores das folhas.\r\n" +
                    "Temperaturas mais frias gradualmente.\r\n" +
                    "Colheitas e preparação para o inverno.");
        }
        if (numero == 4) {
            System.out.println(" Sua estação é o Inverno");
            System.out.println("Estação mais fria do ano.r\n" +
                    "Neve (em muitas regiões) e dias curtos.r\n" +
                    "Plantas em dormência e animais hibernando.r\n" +
                    "Atividades limitadas ao ar livre e festividades de inverno.");
        }

        dadosScanner.close();
    }
}
