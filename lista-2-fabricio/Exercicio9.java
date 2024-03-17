import java.util.Scanner;

public class Exercicio9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double peso, altura, imc, pesoIdeal, pesoAjustado;
        String genero;

        System.out.print("Qual seu gênero? (M = Masculino e F = Feminino): ");
        genero = scanner.nextLine();
        
        System.out.print("Digite seu peso atual em quilogramas: ");
        peso = scanner.nextDouble();
        
        System.out.print("Digite sua altura em centímetros: ");
        altura = scanner.nextDouble();
        
        imc = calcularIMC(peso, altura);
        System.out.println("Seu IMC é: " + String.format("%.1f", imc));
        
        pesoIdeal = calcularPesoIdeal(altura, genero);
        System.out.println("Seu peso ideal é: " + String.format("%.1f", pesoIdeal) + " kg");
        
        pesoAjustado = calcularPesoIdealAjustado(peso, pesoIdeal);
        System.out.println("Seu peso ideal ajustado é: " + String.format("%.1f", pesoAjustado) + " kg");
        
        scanner.close();
    }

    public static double calcularIMC(double peso, double altura) {
        double alturaMetros = altura / 100;
        return peso / (alturaMetros * alturaMetros);
    }
    
    public static double calcularPesoIdeal(double altura, String genero) {
        if(genero.equals("M")) {
            return 52 + (0.75 * (altura - 152.4));
        }

        return 52 + (0.67 * (altura - 152.4));
    }
    
    public static double calcularPesoIdealAjustado(double peso, double pesoIdeal) {
        return ((peso - pesoIdeal) * 0.25) + pesoIdeal;
    }
}
