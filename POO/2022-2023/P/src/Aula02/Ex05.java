package Aula02;
import java.util.Scanner;

public class Ex05 {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        double d1, v1, d2, v2;
        System.out.print("Distância 1:");
        d1 = sc.nextDouble();
        assert d1 >= 0 : "Distância não pode ser negativa";
        System.out.print("Velocidade 1:");
        v1 = sc.nextDouble();
        assert v1 > 0 : "Velocidade não pode ser negativa";
        System.out.print("Distância 2:");
        d2 = sc.nextDouble();
        assert d2 >= 0 : "Distância não pode ser negativa";
        System.out.print("Velocidade 2:");
        v2 = sc.nextDouble();
        assert v2 > 0 : "Velocidade não pode ser negativa";
        double v = (v1 * d1+v2 * d2 )/ (d1 + d2);
        System.out.printf("O montante ao fim de meses será %.3f€",v);
    }
    
}
