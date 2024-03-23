/*Escreva um programa em Java para ler o número total de eleitores de um município, 
o número de votos brancos, nulos e válidos. Calcular e escrever o percentual que cada um representa 
em relação ao total de eleitores. */

import java.util.Scanner;

public class Questao3 {

    public static void main(String[] args) {
        Scanner dadosScanner = new Scanner(System.in);
        int brancos, nulos, validos, totalVotos;
        double porcentagemBrancos, porcentagemNulos, porcentagemValidos;

        System.out.println("Vamos calcular o total de eleitores de um município!");
        System.out.println("Digite a quantidade dos votos bracos: ");
        brancos = dadosScanner.nextInt();

        System.out.println("Digite a quantidade dos votos nulos: ");
        nulos = dadosScanner.nextInt();

        System.out.println("Digite a quantidade dos votos válidos: ");
        validos = dadosScanner.nextInt();

        totalVotos = brancos + nulos + validos;
        
        porcentagemBrancos = (brancos * 100) / totalVotos;
        porcentagemNulos = (nulos * 100) / totalVotos;
        porcentagemValidos = (validos * 100) / totalVotos;

        System.out.println("O total de votos do município foi: " + totalVotos);

        System.out.println("O total de votos brancos foram " + brancos + " correspodendo " + porcentagemBrancos + "%");
        System.out.println("O total de votos nulos foram " + nulos + " correspodendo " + porcentagemNulos + "%");
        System.out.println("O total de votos validos foram " + validos + " correspodendo " + porcentagemValidos + "%");

        dadosScanner.close();
    }

}
