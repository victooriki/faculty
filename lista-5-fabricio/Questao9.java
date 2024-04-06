/*  Fazer um algoritmo, utilizando o comando for, que calcule e escreva a soma dos
    50 primeiros termos da seguinte série: S = 1000 / 1 - 997 / 2 + 994 / 3 - 991 / 4 + ...   */

public class Questao9 {
    public static void main(String[] args) {
        StringBuilder serie = new StringBuilder();
        double soma = 0.0;
        int numerador = 1000;
        int denominador = 1;
        int sinal = 1;

        for (int i = 1; i <= 50; i++) {
            serie.append(numerador).append(" / ").append(denominador);
            if (i < 50) {
                serie.append(sinal > 0 ? " + " : " - ");
            }
            soma += (double) sinal * numerador / denominador;
            numerador -= 3;
            denominador++;
            sinal *= -1;
        }

        System.out.println("A soma dos 50 primeiros termos da série é: " + soma);
        System.out.println("Série: " + serie.toString());
    }
}