import java.util.Scanner;

public class Exercicio9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        for (int index = 0; index <= 49; index++) {
            index++; 
            System.out.print(index + (index != 49 ? ", " : ""));
        }

        scanner.close();
    }
}