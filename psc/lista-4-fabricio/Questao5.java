/*Crie um programa em Java que implementa um jogo simples de adivinhação.
O objetivo do jogo é que o usuário tente adivinhar um número secreto gerado aleatoriamente pelo computador. 
Este número estará entre 1 e 100, inclusive. Para tornar o jogo interativo e dar feedback ao jogador,
o programa deve informar após cada tentativa se o palpite do usuário é muito alto, muito baixo, ou correto. 
O jogo termina quando o usuário acertar o número, e o programa deve informar o número de tentativas que foram
necessárias para chegar à resposta correta.*/

import java.util.Random;
import java.util.Scanner;

public class Questao5 {

    public static void main(String[] args) {
        Scanner dadosScanner = new Scanner(System.in);
        Random numeroAleatorio= new Random();

        int numero;
        
        while (true) {
            int adivinhacao = numeroAleatorio.nextInt(101); 
            int tentativas = 0;

            while (true) {
                System.out.println("Bem-Vindo ao jogo da adivinhação!");
                System.out.println("Digite um número de 0 a 100: ");
                numero=dadosScanner.nextInt();
                tentativas++;

                if (numero > adivinhacao) {
                System.out.println("Você foi longe! O número é menor ");
                }else if(numero < adivinhacao){
                System.out.println("Você foi longe! O número é maior ");
                }if (numero == adivinhacao) {
                break;
                }
            }
            System.out.println("Parabéns você acertou :)");
            System.out.println("O número de tentativas foram: "+ tentativas);
            dadosScanner.close();   
        }
    }
}
