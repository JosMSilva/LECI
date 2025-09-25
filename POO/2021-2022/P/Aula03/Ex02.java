package Aula03;
import java.util.Scanner;


public class Ex02 {
    static Scanner sc = new Scanner(System.in);
    public static void main (String[] args){
        System.out.print("Introduza o valor: ");
        int temp = sc.nextInt();
        int N = numCheck(temp);
        for(int i = N; i >= 0; i-- ){
            System.out.println(i);
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
