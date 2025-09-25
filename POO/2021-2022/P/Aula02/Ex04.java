package Aula02;
import java.util.Scanner;

public class Ex04 {
    static Scanner sc = new Scanner(System.in);

    public static void main (String[] args){
        double money;
        do{
        System.out.print("Introduza o montante inicial: ");
        money = sc.nextDouble(); 
        if(money <= 0){
            System.err.println("Quantidade não válida");
        }

        }while(money <= 0);
        double meses;
        do{
        System.out.print("Introduza o número de meses: ");
        meses = sc.nextDouble(); 
        if(meses <= 0){
            System.err.println("Quantidade não válida");
        }

        }while(meses <= 0);
        double juros;
        do{
            System.out.print("Introduza valor de juro: ");
            juros = sc.nextDouble(); 
            if(juros <= 0){
                System.err.println("Quantidade não válida");
            }
            juros = juros/100;
        }while(juros <= 0);
        double fmoney = money;
        for(int i = 1; i <= meses; i++){
            fmoney = fmoney + fmoney*juros;
        }
        System.out.println(fmoney);
    }
    
}
