/* Crie um programa em Java que exiba formas geométricas na tela. Um menu deve ser exibido com as seguintes opções: retângulo, diagonal superior esquerda, diagonal superior direita, diagonal inferior esquerda, diagonal inferior direita. As imagens devem ser formadas por asteriscos (*) e, a pessoa utilizadora deve informar a quantidade de colunas. Abaixo estão alguns exemplos das formas: */

import java.util.Scanner;

public class Questao7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int colunas, forma;

        do {
            System.out.print("Digite o número de colunas (maior que 0): ");
            colunas = input.nextInt();
        } while (colunas <= 0);

        System.out.println("Escolha a sua forma geométrica: \n1 - retângulo\n2 - diagonal superior esquerda\n3 - diagonal superior direita\n4 - diagonal inferior esquerda\n5 - diagonal inferior direita");
        forma = input.nextInt();

        switch (forma) {
            case 1:
                for (int i = 0; i < colunas; i++) {
                    for (int j = 0; j < colunas; j++) {
                        System.out.print("* ");
                    }
                    System.out.println(" ");
                }
                break;
            case 2:
                for (int i = 0; i < colunas; i++) {
                    for (int j = 0; j < colunas; j++) {
                        if (j >= i) {
                            System.out.print("* ");
                        } else {
                            System.out.print("");
                        }
                    }
                    System.out.println();
                }
                break;
            case 3:
                for (int i = 0; i < colunas; i++) {
                    for (int j = 0; j < colunas; j++) {
                        if (j >= i) {
                            System.out.print("* ");
                        } else {
                            System.out.print("  ");
                        }
                    }
                    System.out.println();
                }
                break;
            case 4:
                for (int i = 0; i < colunas; i++) {
                    for (int j = 0; j < colunas; j++) {
                        if (j <= i) {
                            System.out.print("* ");
                        } else {
                            System.out.print("");
                        }
                    }
                    System.out.println();
                }
                break;
            case 5:
                for (int i = 0; i < colunas; i++) {
                    for (int j = 0; j < colunas; j++) {
                        if (j >= colunas - i - 1) {
                            System.out.print("* ");
                        } else {
                            System.out.print("  ");
                        }
                    }
                    System.out.println();
                }
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
        input.close();
    }
}
