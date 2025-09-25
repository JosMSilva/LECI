package Aula02;
import java.util.Scanner;

public class Ex02 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        double celsius,fahrenheit;
        System.out.print("Insira temperatura em Celsius:");
        celsius = sc.nextDouble();
        fahrenheit = celsius * 1.8 + 32;
        System.out.printf("A temperatura em Fahrenheit é %.2f",fahrenheit);
        sc.close();
    }
}