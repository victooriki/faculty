import java.util.Scanner;

public class Exercicio13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double valorHora, porcentagemInss, desconto, salarioBruto, salarioLiquido;
        int horasMes;

        System.out.println("Escreva o valor da hora de trabalho:");
        valorHora = scanner.nextDouble();

        System.out.println("Escreva o número de horas trabalhadas no mês:");
        horasMes = scanner.nextInt();

        System.out.println("Escreva o percentual de desconto do INSS");
        porcentagemInss = scanner.nextDouble();

        salarioBruto = valorHora * horasMes;
        desconto = (porcentagemInss / 100) * salarioBruto;
        salarioLiquido = salarioBruto - desconto;

        System.out.print(String.format("Salário bruto: R$%s\nDesconto do INSS: R$%s\nSalário líquido: R$%s",
        salarioBruto, desconto, salarioLiquido));
        
        scanner.close();
    }
}