package Aula02;
import java.util.Scanner;

public class Ex02 {
    static Scanner sc = new Scanner(System.in);

    public static void main (String[] args){
        double degrees;
        System.out.println("Introduza um valor");
        degrees = sc.nextDouble(); 
        double far = (degrees/1.8) + 32;
        System.out.printf("A Tempratura em Fahrenheit é %.2f",far);
    }
    
}
