/*Faça um programa que calcule o tempo necessário para o download de um arquivo qualquer da internet.
 Deve-se informar o tamanho do arquivo em MB, a velocidade do link em Mbps e, retornar
 o tempo total em minutos */

 import java.util.Scanner;

 public class Exercicio8 {
    public static void main(String[] args) {
        Scanner dadosScanner= new Scanner(System.in);

        double tamanhoArquivo,velocidadeLink, resultadoTempo,tamanhoArquivoMB,velocidadeLinkMbps;

        System.out.println("Vamos calcular a quantidade de tempo necessário para o dwnload!");
        System.out.println("Informe o tamanho do arquivo em MB: ");
        tamanhoArquivoMB= dadosScanner.nextDouble();

        System.out.println("Informe a velocidade do link em Mbps: ");
        velocidadeLinkMbps=dadosScanner.nextDouble();

        tamanhoArquivo = tamanhoArquivoMB * 8 * 1024 * 1024;

        velocidadeLink = velocidadeLinkMbps * 1024 * 1024 / 60;

        resultadoTempo= velocidadeLink/tamanhoArquivo;

        System.out.println("O tempo necessário em minutos é: "+ resultadoTempo);
        dadosScanner.close();
       
    } 
    
 }