/*Crie um programa para exibir o menu de um caixa eletrônico. Ele deve
possuir as opções: Exibir saldo, Exibir extrato, Realizar depósito, Realizar
saque, e Sair. Se alguma opção inválida for fornecida, exiba uma mensagem
de erro e saia do programa. Observação: Utilize a estrutura de seleção
Switch/Case para implementar seu produto. Utilize números inteiros para
indicar cada opção. */

import java.util.Scanner;

 public class Questao11 {
 
    public static void main(String[] args) {
      Scanner dadosScanner= new Scanner(System.in);

      System.out.println("Bem-vindo ao Caixa Eletrônico!");
      System.out.println("Selecione uma opção:");
      System.out.println("1. Exibir saldo");
      System.out.println("2. Exibir extrato");
      System.out.println("3. Realizar depósito");
      System.out.println("4. Realizar saque");
      System.out.println("5. Sair");
      int opcao = dadosScanner.nextInt();

      switch (opcao) {
        case 1:
            System.out.println("Exibindo saldo...");
            break;

        case 2:
            System.out.println("Exibindo extrato...");
            break;

        case 3:
            System.out.println("Realizando depósito...");
            break;

        case 4:
            System.out.println("Realizando saque...");
            break;

        case 5:
            System.out.println("Saindo do programa. Obrigado!");
            break;

        default:
            System.out.println("Opção inválida. Saindo do programa.");
    }

    dadosScanner.close();
 }
}
