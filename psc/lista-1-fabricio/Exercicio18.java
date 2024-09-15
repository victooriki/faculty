import java.util.Scanner;
import java.lang.Math;

public class Exercicio18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double tamanhoDegrau, tamanhoDesejado;
        int qtdDegraus;


        System.out.print("Digite a altura de cada degrau (em metros): ");
        tamanhoDegrau = scanner.nextDouble();

        System.out.print("Digite a altura que deseja alcançar (em metros): ");
        tamanhoDesejado = scanner.nextDouble();

        qtdDegraus = (int) Math.ceil(tamanhoDesejado / tamanhoDegrau);

        System.out.print(String.format("Você precisa subir %s degraus para alcançar a altura desejada.", 
        qtdDegraus));
        
        scanner.close();
    }
}