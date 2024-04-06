/*  Faça um programa que apresente o menu de opções a seguir, permita ao usuário escolher a opção desejada, receba os dados necessários para executar a operação e mostre o resultado. Verifique a possibilidade de opção inválida e não se preocupe com restrições do tipo salário inválido.
    Menu de opções:
    1. Imposto
    2. Novo salário
    3. Classificação
    4. Finalizar o programa  */

import java.util.Scanner;

public class Questao6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("Menu de opções:");
            System.out.println("1. Imposto");
            System.out.println("2. Novo salário");
            System.out.println("3. Classificação");
            System.out.println("4. Finalizar o programa");
            System.out.print("Digite a opção desejada: ");
            opcao = input.nextInt();
            
            switch (opcao) {
                case 1:
                    calcularImposto(input);
                    break;
                case 2:
                    calcularNovoSalario(input);
                    break;
                case 3:
                    classificarSalario(input);
                    break;
                case 4:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
            
            System.out.println();
        } while (opcao != 4);
        
        input.close();
    }
    
    public static void calcularImposto(Scanner input) {
        System.out.print("Digite o salário do funcionário: ");
        double salario = input.nextDouble();
        
        double imposto;
        if (salario < 1400.00) {
            imposto = salario * 0.05;
        } else if (salario >= 1400.00 && salario <= 2500.00) {
            imposto = salario * 0.10;
        } else {
            imposto = salario * 0.15;
        }
        
        System.out.println("Valor do imposto: R$" + imposto);
    }
    
    public static void calcularNovoSalario(Scanner input) {
        System.out.print("Digite o salário do funcionário: ");
        double salario = input.nextDouble();
        
        double aumento;
        if (salario > 5000.00) {
            aumento = 25.00;
        } else if (salario >= 2500.00 && salario <= 5000.00) {
            aumento = 50.00;
        } else if (salario >= 1400.00 && salario <= 2500.00) {
            aumento = 75.00;
        } else {
            aumento = 100.00;
        }
        
        double novoSalario = salario + aumento;
        System.out.println("Novo salário: R$" + novoSalario);
    }
    
    public static void classificarSalario(Scanner input) {
        System.out.print("Digite o salário do funcionário: ");
        double salario = input.nextDouble();
        
        String classificacao = (salario <= 1400.00) ? "Mal remunerado" : "Bem remunerado";
        System.out.println("Classificação: " + classificacao);
    }
}