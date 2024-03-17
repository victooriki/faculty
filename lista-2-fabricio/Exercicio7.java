/*Incremente o programa da questão 6, para informar o valor do metro quadrado da cerâmica 
escolhido e o valor total a ser pago. */

import java.util.Scanner;

public class Exercicio7 {

    public static void main(String[] args) {
        Scanner dadosScanner = new Scanner(System.in);

        double comprimentoArea, larguraArea, larguraCeramica, comprimentoCeramica, valorMetro;
        double totalComprimentoArea, totalComprimentoCeramica, totallarguraArea, totallarguraCeramica;
        double arredondaComprimentoArea, arredondaComprimentoCeramica, arredondaLarguraArea, arredondaLarguraCeramica;
        double totalCeramica, totalArea, valorTotalCeramica, valorTotalArea;

        System.out.println("Vamos calcular a quantidade de revestimento necessário para sua obra!");
        System.out.println("Informe o valor do metro quadrado da cerâmica:");
        valorMetro = dadosScanner.nextDouble();

        System.out.println("Informe a largura da sua área: ");
        larguraArea = dadosScanner.nextDouble();

        System.out.println("Informe o comprimento da sua área: ");
        comprimentoArea = dadosScanner.nextDouble();

        System.out.println("Agora me informe a largura da cerâmica: ");
        larguraCeramica = dadosScanner.nextDouble();

        System.out.println("Qual é o comprimento da cerâmica: ");
        comprimentoCeramica = dadosScanner.nextDouble();

        totalComprimentoArea = valorMetro * (comprimentoArea + (comprimentoArea * 0.10));
        totalComprimentoCeramica = valorMetro * (comprimentoCeramica + (comprimentoCeramica * 0.10));
        totallarguraArea = valorMetro * (larguraArea + (larguraArea * 0.10));
        totallarguraCeramica = valorMetro * (larguraCeramica + (larguraCeramica * 0.10));

        arredondaComprimentoArea = Math.ceil(totalComprimentoArea);
        arredondaComprimentoCeramica = Math.ceil(totalComprimentoCeramica);
        arredondaLarguraArea = Math.ceil(totallarguraArea);
        arredondaLarguraCeramica = Math.ceil(totallarguraCeramica);

        totalCeramica = arredondaComprimentoCeramica + arredondaLarguraCeramica;
        totalArea = arredondaComprimentoArea + arredondaLarguraArea;

        valorTotalCeramica = totalCeramica * valorMetro;
        valorTotalArea = totalArea * valorMetro;

        System.out.println("O valor total da área é " + valorTotalArea);
        System.out.println("O valor total da cerâmica é: " + valorTotalCeramica);
        System.out.println("Esses valores estão com 10% a mais para o acabamento!");

        dadosScanner.close();
    }
}

    
