package Aula02;
import java.util.Scanner;

public class Ex07 {
    static Scanner sc = new Scanner(System.in);

    public static void main (String[] args){
        double p1x,p1y,p2x,p2y,a,c,d2,d;

        System.out.print("Introduza o x de p1: ");
        p1x = sc.nextDouble();
        System.out.print("Introduza o y de p1: ");
        p1y = sc.nextDouble();
        System.out.print("Introduza o x de p2: ");
        p2x = sc.nextDouble();
        System.out.print("Introduza o y de p2: ");
        p2y = sc.nextDouble(); 
        

        a = p2x - p1x;
        a = a*a;
        c = p2y - p1y;
        c = c*c;
        d2 = a + c;
        d = Math.sqrt(d2);

        System.out.println(d);
    }
    
}

