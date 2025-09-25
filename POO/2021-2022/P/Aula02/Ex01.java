package Aula02;
import java.util.Scanner;

public class Ex01 {
    static Scanner sc = new Scanner(System.in);

    public static void main (String[] args){
        double kms;
        System.out.println("Introduza um valor");
        do{
            kms= sc.nextDouble(); 
            if(kms < 0)
            {
                System.err.println("Valor inválido");
            }
        }
        while(kms<0);
        double miles = kms/1.609;
        System.out.println("A distancia em milhas é " + miles);
    }
    
}
