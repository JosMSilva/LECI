package Aula03;
import java.util.Scanner;


public class Ex04 {
    static Scanner sc = new Scanner(System.in);
    public static void main (String[] args){
        System.out.print("Introduza o primeiro valor: ");
        double first = sc.nextDouble();
        double min = first;
        double max = first;
        double med = first;
        int i = 1;
        double num;
        do{
            System.out.print("Introduza outro valor: ");
            num = sc.nextDouble();
            i++;
            min = checkMin(num, min);
            max = checkMax(num, max);
            med += num;
        }while(num != first);
        med = med/i;
        System.out.println("Minimo é: " + min);
        System.out.println("Máximo é: " + max);
        System.out.println("Média é: " + med);
        System.out.println("Total de numeros é: " + i);
        
    }
    static  double checkMin (double N,double min){
        if(N < min){
            min = N;
        }
    return min;
}

    static  double checkMax (double N,double max){
            if(N > max){
                max = N;
            }
        return max;
    }

    
}
