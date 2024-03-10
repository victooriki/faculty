public class Exercicio19 {
    public static void main(String[] args) {
        System.out.println("Número\tQuadrado\tCubo");
        int num, quadrado, cubo;

        for (num = 0; num <= 10; num++) {
            quadrado = num * num;
            cubo = num * num * num;

            System.out.printf("%d\t%d\t\t%d\n", num, quadrado, cubo);
        }
    }
}