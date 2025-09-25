package Aula03;
import java.util.Scanner;


public class Ex03 {
    static Scanner sc = new Scanner(System.in);
    public static void main (String[] args){
        System.out.print("Introduza o valor: ");
        int temp = sc.nextInt();
        int N = numCheck(temp);
        boolean primo = true;
        for(int i = 2; i < N-1; i++ ){
            if(N%i == 0)
            {
                primo = false;
            }
        }
        if(primo){
            System.out.println(N + " é primo");
        }else{
            System.out.println(N + " não é primo");
        }
        
    }

    static  int numCheck (int N){
        do{
            if(N < 0){
                System.err.println("Valor menor que 0");
                N = sc.nextInt();
            }
        }while(N < 0);
        return N;
    }
    
}
