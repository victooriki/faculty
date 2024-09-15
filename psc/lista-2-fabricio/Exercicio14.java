/* Escreva um algoritmo que recebe dois pontos no plano cartesiano,
P_1=(x_1,y_1) e P_2=(x_2,y_2)e calcula a distância entre eles, 
dada por d(P_1,P_2)=√(〖(x〗_1-x_2 )^2+〖(y〗_1-y_2 )^2 )  */

import java.util.Scanner;

public class Exercicio14 {
    public static void main(String[] args) {
        Scanner dadosScanner = new Scanner(System.in);

        System.out.println("Digite as coordenadas do ponto P1 (x1, y1): ");
        double x1 = dadosScanner.nextDouble();
        double y1 = dadosScanner.nextDouble();

        System.out.println("Digite as coordenadas do ponto P2 (x2, y2): ");
        double x2 = dadosScanner.nextDouble();
        double y2 = dadosScanner.nextDouble();

        double distancia = sqrt(square(x1 - x2) + square(y1 - y2));

        System.out.println("A distância entre os pontos P1 e P2 é: " + distancia);

        dadosScanner.close();
    }

    public static double square(double num) {
        return num * num;
    }

    public static double sqrt(double num) {
        double approx = num / 2.0;
        double lastApprox = 0.0;
        while (approx != lastApprox) {
            lastApprox = approx;
            approx = (approx + num / approx) / 2.0;
        }
        return approx;
    }
}
