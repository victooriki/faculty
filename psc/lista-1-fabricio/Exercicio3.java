import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String name;

        System.out.print("Escreva seu nome: ");
        name = scanner.nextLine();

        System.out.print(String.format("Seja bem-vindo %s ao portal da Una", name));
        scanner.close();
    }
}