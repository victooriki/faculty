import java.util.Scanner;

public class Exercicio4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name, endereco, tel, resultTel;

        System.out.print("Escreva seu nome: ");
        name = scanner.nextLine();

        System.out.print("Escreva seu endereço: ");
        endereco = scanner.nextLine();

        System.out.print("Escreva seu número de telefone (ex: 31992374646): ");
        tel = scanner.nextLine();
        resultTel = formatTel(tel);

        System.out.print(String.format("Seus dados são: \nNome: %s \nEndereço: %s \nTelefone: %s", name, endereco, resultTel));
        scanner.close();
    }

    private static String formatTel(String numero) {
        if (numero.length() == 11) {
            return String.format("(%s) %s %s-%s",
                    numero.substring(0, 2),
                    numero.substring(2, 3),
                    numero.substring(3, 7),
                    numero.substring(7));
        }

        return "Número de telefone inválido";
    }
}