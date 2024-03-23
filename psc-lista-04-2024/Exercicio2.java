/*Faça um programa que leia um nome de usuário e a sua senha e não aceite a
senha igual ao nome do usuário, mostrando uma mensagem de erro e
voltando a pedir as informações. */

import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {

        Scanner dadosScanner= new Scanner(System.in);

        String nome, senha;
        for (;;) {
            System.out.println("Digite seu nome de usuário: ");
            nome = dadosScanner.nextLine();

            System.out.println("Digite sua senha: ");
            senha = dadosScanner.nextLine();

            if (nome.equals(senha)) {
                System.out.println("Erro: A senha não pode ser igual ao nome de usuário.");
                
            } else {
                System.out.println("Usuário e senha cadastrados com sucesso!");
                dadosScanner.close();
                break;

            }
        }
    }
}
