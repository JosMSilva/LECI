package Aula03;
import java.util.Scanner;
import java.util.Random;

public class Ex07 {

    static Scanner sc = new Scanner(System.in);
    public static void main (String[] args){
        Random r = new Random();
        int rand = r.nextInt(1, 101);
        int tr;
        int att = 0;
        String s = "S";

        while(s == "S" || s == "SIM"){
            System.out.print(rand);
            System.out.print("Introduza o numero entre 1 e 100: ");
            tr = sc.nextInt();
            att += 1; 
            while(tr > 100 || tr < 1){
                System.err.println("Quantidade não válida");
                tr = sc.nextInt();
            }
            if(tr == rand){
                System.out.printf("Acertou com %d tentativas, deseja continuar?", att);

                s = sc.next();
                s = s.toUpperCase();
                System.out.print(s);
                if(s == "S" || s == "SIM"){
                    rand = r.nextInt(1, 101);  
                    att = 0;
                }
            }
    
        }

    }
    
}
