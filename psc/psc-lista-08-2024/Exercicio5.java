import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double taxaImposto;
        double custo;

        System.out.print("Digite o valor do importo: ");
        taxaImposto = scanner.nextInt();

        System.out.print("Digite o valor do custo: ");
        custo = scanner.nextInt();
        
        double custoComImposto = somaImposto(taxaImposto, custo);
        System.out.println("Custo com imposto incluído: R$ " + custoComImposto);
        scanner.close();
    }

    public static double somaImposto(double taxaImposto, double custo) {
        double custoComImposto = custo + (custo * (taxaImposto / 100));
        
        return custoComImposto;
    }
}
