package Aula02;
import java.util.Scanner;

public class Ex08 {
    static Scanner sc = new Scanner(System.in);

    public static void main (String[] args){
        double A,B,C,B1,SA,d,d2;

        do{
            System.out.print("Introduza o lado A: ");
            A = sc.nextDouble(); 
            if(A <= 0){
                System.err.println("Quantidade não válida");
            }
    
            }while(A <= 0);
            do{
                System.out.print("Introduza o lado B: ");
                B = sc.nextDouble(); 
                if(B <= 0){
                    System.err.println("Quantidade não válida");
                }
        
                }while(B <= 0);
        
        
        A = A*A;
        B1 = B*B;
        C = A + B1;
        d = Math.sqrt(C);
        SA = B/C;
        d2 = Math.sinh(SA);


        System.out.println("C = " + d + "e o angulo é " + d2);
    }
    
}

