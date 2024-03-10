import java.util.Scanner;

public class Exercicio16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double distanciaKm, qtdLitros, consumoMedio;

        System.out.println("Escreva a entrada a distância total (em km) percorrida:");
        distanciaKm = scanner.nextDouble();

        System.out.println("Escreva a quantidade de combustível (em litros) consumida");
        qtdLitros = scanner.nextDouble();

        consumoMedio = distanciaKm / qtdLitros;

        System.out.print(String.format("O consumo médio de combustível é %s litros por km", 
        consumoMedio));
        
        scanner.close();
    }
}