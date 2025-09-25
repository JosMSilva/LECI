package Aula03;
import java.util.Scanner;

public class Ex06 {
    static Scanner sc = new Scanner(System.in);

    public static void main (String[] args){
        int month,year;

        do{
            System.out.print("Introduza o mês: ");
            month = sc.nextInt(); 
            if(!validMonth(month)){
                System.err.println("Quantidade não válida");
            }
    
        }while(!validMonth(month));

        System.out.print("Introduza o ano: ");
        year = sc.nextInt();

        System.out.println(monthDays(month, year));
    }
    public static boolean validMonth(int month){
        return month >= 1 && month <= 12;
    }

    public static int monthDays(int month, int year){
        assert validMonth(month);
        if (month == 2){
            if(leapYear(year)){
                return 29;
            }else{
                return 28;
            }
            
        }else if(month == 4 || month == 6 || month == 9 || month == 11){
            return 30;
        }else{
            return 31;
        }
    }

    public static boolean leapYear(int year){
        return (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0));
    }
    
}

