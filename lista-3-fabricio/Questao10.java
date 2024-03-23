/*O programa deve exibir a mensagem "Lojinha do Sr. Aboo" e em
seguida solicitar o valor da compra com o fornecedor, e, em seguida, exibir
qual será a porcentagem de lucro e o valor que o item deve ser colocado
para venda.
 */

 import java.util.Scanner;

 public class Questao10 {
 
    public static void main(String[] args) {
      Scanner dadosScanner= new Scanner(System.in);
      float  valorCompra;
      double valorVenda=0, lucro= 0;

      System.out.println("Lojinha do Sr.Aboo");
      System.out.println("Digite o valor de compra com o fornecedor: ");
       valorCompra= dadosScanner.nextFloat();

      if (valorCompra < 10) {
        lucro= 70;
        valorVenda= valorCompra +( valorCompra* 0.70);

      }else if (valorCompra <= 10 || valorCompra <30) {
        lucro= 50;
        valorVenda= valorCompra +( valorCompra* 0.50);

      }else if (valorCompra <= 30 || valorCompra < 50) {
        lucro= 40;
        valorVenda= valorCompra +( valorCompra* 0.40);

      }else if (valorCompra >=50) {
        lucro= 30;
        valorVenda= valorCompra +( valorCompra* 0.30);
      }
      
      System.out.println("O lucro foi "+ lucro+"%"+", deve ser vendido por: "+ valorVenda);

    dadosScanner.close();
    }
 }
        