/*Modifique o programa do Jogo de Adivinhação para que após cada tentativa, 
o programa deve informar ao usuário se o palpite é muito alto, muito baixo, ou correto. 
Uma vez que o usuário adivinhe o número corretamente, o programa perguntará se ele gostaria de jogar novamente.
O usuário pode continuar jogando quantas vezes quiser até que escolha sair do jogo. Dica: use while(true).*/
import java.util.Random;
import java.util.Scanner;

public class Questao6 {

    public static void main(String[] args) {
        Scanner dadosScanner = new Scanner(System.in);
        Random numeroAleatorio = new Random();
        int numero;

        System.out.println("Bem-vindo ao jogo da adivinhação!");

        while (true) {
            int adivinhacao = numeroAleatorio.nextInt(101); 
            int tentativas = 0;


        while (true) {

            System.out.println("Digite um número de 0 a 100:");
            numero = dadosScanner.nextInt();
            tentativas++; 

            if (numero > adivinhacao) {
                System.out.println("Você foi longe! O número é menor");
            } else if (numero < adivinhacao) {
                System.out.println("Você foi longe! O número é maior");
            } else {
                System.out.println("Parabéns, você acertou! :)");
                break;
            }
            }
            System.out.println("O número de tentativas foi: " + tentativas);
            System.out.println("Deseja continuar? Digite 'S' para jogar novamente ou 'N' para sair:");
            String resposta = dadosScanner.next();
            if (resposta.equalsIgnoreCase("N")) {
            break; 
        }
    }
        System.out.println("Obrigada por jogar ! ");

        dadosScanner.close();
    }
}



