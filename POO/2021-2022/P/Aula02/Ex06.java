package Aula02;
import java.util.Scanner;

public class Ex06 {
    static Scanner sc = new Scanner(System.in);

    public static void main (String[] args){
        int sec,h,m,s;
        do{
        System.out.print("Introduza o tempo em segundos: ");
        sec = sc.nextInt(); 
        if(sec <= 0){
            System.err.println("Quantidade não válida");
        }

        }while(sec <= 0);

        h = sec/3600;
        m = (sec%3600) / 60;
        s = ((sec%3600)%60);

        System.out.printf("%02d:%02d:%02d\n",h,m,s);
    }
    
}

