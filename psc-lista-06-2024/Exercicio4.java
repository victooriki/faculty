import java.util.Scanner;

public class Exercicio4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[] caracteres = new char[10];
        int totalConsoantes = 0;

        System.out.println("Digite 10 caracteres:");

        for (int i = 0; i < 10; i++) {
            caracteres[i] = input.next().charAt(0);
        }

        System.out.println("Consoantes lidas:");
        for (int i = 0; i < 10; i++) {
            char caracter = caracteres[i];
            if (Character.isLetter(caracter) && !isVogal(caracter)) {
                totalConsoantes++;
                System.out.println(caracter);
            }
        }

        System.out.println("Total de consoantes lidas: " + totalConsoantes);

        input.close();
    }

    public static boolean isVogal(char c) {
        char[] vogais = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
        for (char vogal : vogais) {
            if (c == vogal) {
                return true;
            }
        }
        return false;
    }
}