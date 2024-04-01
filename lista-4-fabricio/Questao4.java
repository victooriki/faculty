/*Em um campeonato de LOL, enquanto não há a tomada de território,
o contador implementado deve contar (que é contabilizado pelo jogo), 
o número de kills, deaths e assists. Se o número de kills for menor ou igual a 5,
ele mostra a mensagem “noob”, se chegar a 20 ou mais “master”.
Se o número de deaths chegar a 20 ou mais,  ele mostra a mensagem “Houston, we have a problem”.
Se o número de assists chegar a 20 ou mais, é mostrada a mensagem: “team work”. 
Lembre-se, é uma rotina que continua enquanto não houver um vencedor.
Dicas: 
há kills, deaths e assists total e da rodada
medite na frase: enquanto não há um vencedor, faça…
pergunte a cada loop o número de cada medida comentada.
você deve perguntar se há um vencedor a cada loop…*/

import java.util.Scanner;

public class Questao4 {
 
    public static void main(String[] args) {
        Scanner dadosScanner = new Scanner(System.in);

        int kills = 0, deaths = 0 , assists = 0;

        while (true) {
            System.out.println("Bem - vindo ao campeonato de LOL!");
            System.out.println(" Qual o número de kills: ");
            kills=dadosScanner.nextInt();
            System.out.println("Digite o número de deaths: ");
            deaths=dadosScanner.nextInt();
            System.out.println(" Qual o número de assists: ");
            assists=dadosScanner.nextInt();

            if (kills <= 5) { 
                System.out.println("De acordo com seu número de kills vc é Noob...");
            }else if ( kills >= 20) {
                System.out.println( "De acordo com seu número de kills vc é Master...");
            }if (deaths >= 20) { 
            System.out.println("Em relação ao seu número de deaths, temos a seguinte mensagem 'Houston, we have a problem'");
            }if ( assists >= 20) {
                System.out.println("Em relação ao seu número de assists, temos a seguinte mensagem 'team work'");
            }
            System.out.println("Há um vencedor?Digite S para sair");
            String resposta = dadosScanner.next();
            if (resposta.equalsIgnoreCase("s")) {
            break;
            }
        }
        System.out.println("PARABÉNS TEMOS UM VENCEDOR \n Fim do campeonato!");
        dadosScanner.close();
    }
}