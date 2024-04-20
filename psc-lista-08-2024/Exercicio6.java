import java.util.Scanner;

public class Exercicio6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] partes;
        int horas, minutos;
        String horarioConvertido, resposta;
        
        while (true) {
            System.out.print("Digite as horas e minutos no formato (HH:MM): ");
            String entrada = scanner.nextLine();
            
            partes = entrada.split(":");
            horas = Integer.parseInt(partes[0]);
            minutos = Integer.parseInt(partes[1]);
            
            horarioConvertido = converteHorario(horas, minutos);
            
            System.out.println("O horário convertido é: " + horarioConvertido);
            
            System.out.print("Deseja converter outro horário? (s/n): ");
            resposta = scanner.nextLine();
            
            if (!resposta.equalsIgnoreCase("s") || !resposta.equalsIgnoreCase("S")) {
                break;
            }
        }
        
        scanner.close();
    }

    public static String converteHorario(int horas, int minutos) {
        char periodo;
        
        if (horas >= 12) {
            periodo = 'P';
            if (horas > 12) {
                horas -= 12;
            }
        } else {
            periodo = 'A';
            if (horas == 0) {
                horas = 12;
            }
        }
        
        return String.format("%d:%02d %c.M.", horas, minutos, periodo);
    }
}
