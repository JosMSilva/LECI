package Aula02;
import java.util.Scanner;

public class Ex03 {
    static Scanner sc = new Scanner(System.in);

    public static void main (String[] args){
        double water;
        do{
        System.out.print("Introduza a quantidade de água em Kg ou L: ");
        water = sc.nextDouble(); 
        if(water <= 0){
            System.err.println("Quantidade não válida");
        }

        }while(water <= 0);
        double temp1;
        double temp2;
        do{
        System.out.print("Introduza a temperatura inicil da água: ");
        temp1 = sc.nextDouble();
        System.out.print("Introduza a temperatura final da água: ");
        temp2 = sc.nextDouble();
        if(temp2<temp1){
            System.err.println("Temperatra final menor que a inicial");
        }
        }while(temp2<temp1);

        double energy = 4184*(temp2-temp1)*water;
        System.out.println("Serão necessários " + energy + " Joules");
    }
    
}
