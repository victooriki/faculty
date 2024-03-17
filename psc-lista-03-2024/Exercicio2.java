import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double valorCompra, valorPago, valorTroco;
        int[] valoresNotas = {50, 20, 10, 5, 2, 1};

        System.out.print("Digite o valor da compra: R$");
        valorCompra = scanner.nextDouble();

        System.out.print("Digite o valor pago: R$");
        valorPago = scanner.nextDouble();

        if(valorCompra > valorPago){
            System.out.println("A quantia paga é insuficiente para realizar a compra. Realize o pagamento correto novamente.");
            scanner.close();
            return;
        }

        valorTroco = valorPago - valorCompra;
        
        System.out.println("Seu troco é: R$ " + valorTroco);
        for (int nota : valoresNotas) {
            int quantidadeNota = (int) (valorTroco / nota);
            valorTroco -= quantidadeNota * nota;
            if (quantidadeNota == 1) {
                System.out.println("Em " + quantidadeNota + " nota de R$ " + nota);
            }

            if (quantidadeNota > 1) {
                System.out.println("Em " + quantidadeNota + " notas de R$ " + nota);
            }
        }

        scanner.close();
    }
}