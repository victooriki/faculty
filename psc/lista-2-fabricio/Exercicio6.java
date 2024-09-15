/*Faça um programa para uma loja de cerâmica que ajuda no cálculo da quantidade de revestimento necessário
 para uma obra. A pessoa utilizadora deve informar as medidas de largura e comprimento da área que será 
 revestida e da cerâmica escolhida. Sempre arredonde o valor para cima, e acrescente dez por cento para o
 acabamento. */

 import java.util.Scanner;

 public class Exercicio6 {
 
    public static void main(String[] args) {
        Scanner dadosScanner= new Scanner(System.in);
        
        double comprimentoArea, larguraArea, larguraCeramica, comprimentoCeramica;
        double totalComprimentoArea,totalComprimentoCeramica, totallarguraArea,totallarguraCeramica;
        double arredondaComprimentoArea,arredondaComprimentoCeramica,arredondaLarguraArea,arredondaLarguraCeramica;


        System.out.println("Vamos calcular a quantidade de revestimento necessário para sua obra!");
        System.out.println("Informe a largura da sua área: ");
        larguraArea= dadosScanner.nextDouble();

        System.out.println("Informe o comprimento da sua área: ");
        comprimentoArea=dadosScanner.nextDouble();

        System.out.println("Agora me informe a largura da cerâmica: ");
        larguraCeramica=dadosScanner.nextDouble();

        System.out.println("Qual é o comprimento da cerâmica: ");
        comprimentoCeramica=dadosScanner.nextDouble();

        totalComprimentoArea = (comprimentoArea+(comprimentoArea * 0.10));
        totalComprimentoCeramica= (comprimentoCeramica+(comprimentoCeramica * 0.10));
        totallarguraArea= (larguraArea+(larguraArea* 0.10));
        totallarguraCeramica= (larguraCeramica+(larguraCeramica* 0.10));

        arredondaComprimentoArea =Math.ceil(totalComprimentoArea);
        arredondaComprimentoCeramica=Math.ceil(totalComprimentoCeramica);
        arredondaLarguraArea=Math.ceil(totallarguraArea);
        arredondaLarguraCeramica=Math.ceil(totallarguraCeramica);

        System.out.println("A largura total da área é "+ arredondaLarguraArea +" e seu comprimento total é "+ arredondaComprimentoArea);
        System.out.println("A largura total da cerâmica é "+ arredondaLarguraCeramica +" e seu comprimento total é "+ arredondaComprimentoCeramica);
        System.out.println("Esses valores estão com 10% a mais para o acabamento!");

        dadosScanner.close();

    }
 }