package Aula02;
import java.util.Scanner;

public class Ex04 {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        double initialM, juro;
        int meses;
        System.out.print("Montante Inicial:");
        initialM = sc.nextDouble();
        assert initialM >= 0 : "Montante Inicial não pode ser negativo";
        System.out.print("Taxa de Juro:");
        juro = sc.nextDouble();
        assert juro >= 0 : "Taxa de Juro não pode ser negativa";
        System.out.print("Número de Meses:");
        meses = sc.nextInt();
        assert meses >= 0 : "Número de Meses não pode ser negativo";
        for(int i = 0; i < meses; i++){
            initialM = initialM + (initialM * juro/100);
        }
        System.out.printf("O montante ao fim de %d meses será %.3f€",meses,initialM);
    }
    
}
