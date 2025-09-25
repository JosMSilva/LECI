package Aula03;
import java.util.Scanner;


public class Ex01 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Insira o valor:");
        int max = sc.nextInt();
        while(max < 0){
            System.out.print("Valor inválido, insira novamente:");
            max = sc.nextInt();
        }
        if(isPrime(max)){
            System.out.println("É primo");
        }else{
            System.out.println("Não é primo");
        }
        sc.close();
    }

    public static boolean isPrime(int n){
        for (int i = 2; i <= n/2; i++) {
            if(n%i == 0){
                return false;
            }
        }
        return true;
    }
    
}
