package Aula05;
import java.util.Scanner;

public class Ex01 {
    
    static Scanner sc = new Scanner(System.in);
    public static void main (String[] args){
        int end, day, month, year;
        Date data = null;
        boolean edata = false;
        do{
            System.out.println("Date operations:");
            System.out.println("1 - create new date");
            System.out.println("2 - show current date");
            System.out.println("3 - increment date");
            System.out.println("4 - decrement date");
            System.out.println("0 - exit");
            end = sc.nextInt();
            while(end > 4 && end < 0){
                System.err.println("Valor não válido");
                end = sc.nextInt();
            }
            if(end == 1){
                System.out.print("Introduza o dia:");
                day = sc.nextInt();
                System.out.print("Introduza o mês:");
                month = sc.nextInt();
                System.out.print("Introduza o ano:");
                year = sc.nextInt();
                data = new Date(day, month, year);
                System.out.println(data.toString());
                edata = true;
            }else if(end == 2){
                assert edata;
                System.out.println(data.toString());
                


            }else if(end == 3){
                assert edata;
                data.increment();
                System.out.println(data.toString());
                

            }else if(end == 4){
                assert edata;
                data.decrement();
                System.out.println(data.toString());

            }

        }while(end != 0);
    }
}

