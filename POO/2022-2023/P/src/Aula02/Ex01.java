package Aula02;

import java.util.Scanner;

public class Ex01 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        double km, miles;
        System.out.print("Insira distância em km: ");
        km = sc.nextDouble();
        assert km >= 0 : "Distância não pode ser negativa";
        miles = km / 1.609;
        System.out.printf("A distância em milhas é %.3f",miles);
        sc.close();
    }
    
}
