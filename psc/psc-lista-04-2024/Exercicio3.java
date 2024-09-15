/* Faça um programa que leia e valide as seguintes informações:
Nome: maior que 3 caracteres;
Idade: entre 0 e 150;
Salário: maior que zero;
Sexo: 'f' ou 'm';
Estado Civil: 's', 'c', 'v', 'd';
*/


import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {

        Scanner dadosScanner= new Scanner(System.in);

        String nome, sexo, estado;
        int idade;
        double salario;
        System.out.println(" Bem vindo ao programa!ATENÇÃO!coloque somente opcões propostas para o programa não repetir");
       
        do {
            System.out.println("Digite o seu nome: ");
            nome = dadosScanner.nextLine();

            System.out.println("Digite o seu sexo: 'f' ou 'm' ");
            sexo = dadosScanner.nextLine();

            System.out.println("Digite Estado Civil: 's', 'c', 'v', 'd' ");
            estado = dadosScanner.nextLine();

            System.out.println("Digite a sua idade: ");
            idade = dadosScanner.nextInt();
            dadosScanner.nextLine();

            System.out.println("Digite o seu salário: ");
            salario = dadosScanner.nextDouble();
            dadosScanner.nextLine();

        } while (
            nome.length() < 3 ||
            idade > 150 ||
            idade <= 0 ||
            salario < 0 ||
            !sexo.equals("m") && !sexo.equals("f") ||
            !estado.equals("s") && !estado.equals("c") && !estado.equals("v") && !estado.equals("d")
        );

        System.out.println("Usuário cadastrado com sucesso!");
        dadosScanner.close();

    }
}