package Aula02;
import java.util.Scanner;

public class Ex05 {
    static Scanner sc = new Scanner(System.in);

    public static void main (String[] args){
        double d1,v1,d2,v2,d3,p1,p2,t1,t2,f1;
        do{
        System.out.print("Introduza a distancia do primeiro trajeto: ");
        d1 = sc.nextDouble(); 
        if(d1 <= 0){
            System.err.println("Quantidade não válida");
        }

        }while(d1 <= 0);

        do{
        System.out.print("Introduza a velocidade do primeiro trajeto: ");
        v1 = sc.nextDouble(); 
        if(v1 <= 0){
            System.err.println("Quantidade não válida");
        }

        }while(v1 <= 0);

        do{
            System.out.print("Introduza a distancia do primeiro trajeto: ");
            d2 = sc.nextDouble(); 
            if(d2 <= 0){
                System.err.println("Quantidade não válida");
            }
    
            }while(d2 <= 0);

        do{
            System.out.print("Introduza a velocidade do primeiro trajeto: ");
            v2 = sc.nextDouble(); 
            if(v2 <= 0){
                System.err.println("Quantidade não válida");
            }
    
        }while(v2 <= 0);

        d3 = d1 + d2;
        p1 = d1/d3;
        p2 = d2/d3;
        t1 = v1*p1;
        t2 = v2*p2;
        f1 = t1 + t2;

       

        System.out.println(f1);
    }
    
}
