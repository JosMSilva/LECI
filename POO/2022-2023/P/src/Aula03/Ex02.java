package Aula03;
import java.util.Scanner;

public class Ex02 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Insira o montante inicial:");
        int init = sc.nextInt();
        while(init < 0 && init % 1000 != 0){
            System.out.print("Valor inválido, insira novamente:");
            init = sc.nextInt();
        }
        System.out.print("Insira a taxa de juro:");
        int juro = sc.nextInt();
        while(init < 0 && init > 5){
            System.out.print("Valor inválido, insira novamente:");
            init = sc.nextInt();
        }
        
        for (int i = 1; i <= 12; i++) {
            init += init * juro / 100;
            System.out.printf("Mês %d: %d1%n",i,init);
        }
        sc.close();
    }
    
}
