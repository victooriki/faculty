/* Dados dois países, A com população igual a cinco milhões de pessoas, e taxa de natalidade de três por cento ao ano, e, B, com população igual a sete milhões de pessoas e taxa de natalidade de dois por cento ao ano, escreva um programa em Java que calcule iterativamente e exiba em quantos anos a população de A ultrapassará a população de B. */

public class Questao9 {
    public static void main(String[] args) {
        int firstNation = 5000000;
        double natalityFirstNation = 0.03;

        int secondNation = 7000000;
        double natalitySecondNation = 0.02;

        int yers = 0;
        while (firstNation <= secondNation) {
            firstNation += firstNation * natalityFirstNation;
            secondNation += secondNation * natalitySecondNation;
            yers++;
        }

        System.out.println("A população de A ultrapassará a população de B em " + yers + " anos.");
    }
}
