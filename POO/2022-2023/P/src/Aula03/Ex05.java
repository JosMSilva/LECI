package Aula03;
import java.util.Scanner;
import java.util.Random;

public class Ex05 {
    
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        System.out.print("Insira o mês e o ano(mm/yyyy):");
        String[] date = sc.nextLine().split("/");
        assert date.length == 2 : "Formato inválido";
        int month = Integer.parseInt(date[0]);
        assert month > 0 && month < 13 : "Mês inválido";
        int year = Integer.parseInt(date[1]);
        assert year > 0 : "Ano inválido";
        System.out.print("Insira o em que começa o mês:");
        int start = sc.nextInt();
        assert start >= 0 && start < 7 : "Dia inválido";
        int days = 0;
        if(month == 2){
            if(year % 4 == 0){
                if(year % 100 == 0){
                    if(year % 400 == 0){
                        days = 29;
                    }else{
                        days = 28;
                    }
                }else{
                    days = 29;
                }
            }else{
                days = 28;
            }
        }else if(month == 4 || month == 6 || month == 9 || month == 11){
            days = 30;
        }else{
            days = 31;
        }

        System.out.println("");
        switch (month){
            case 1:
                System.out.println("\t\t   Janeiro " + year);
                break;
            case 2:
                System.out.println("\t\t   Fevereiro "+ year);
                break;
            case 3:
                System.out.println("\t\t   Março "+ year);
                break;
            case 4:
                System.out.println("\t\t   Abril "+ year);
                break;
            case 5:
                System.out.println("\t\t   Maio "+ year);
                break;
            case 6:
                System.out.println("\t\t   Junho "+ year);
                break;
            case 7:
                System.out.println("\t\t   Julho "+ year);
                break;
            case 8:
                System.out.println("\t\t   Agosto "+ year);
                break;
            case 9:
                System.out.println("\t\t   Setembro "+ year);
                break;
            case 10:
                System.out.println("\t\t   Outubro "+ year);
                break;
            case 11:
                System.out.println("\t\t   Novembro "+ year);
                break;
            case 12:
                System.out.println("\t\t   Dezembro "+ year);
                break;
        }
        System.out.println("Dom\tSeg\tTer\tQua\tQui\tSex\tSab");
        for (int i = 0; i < start; i++) {
            System.out.print("\t");
        }
        for (int i = 1; i <= days; i++) {
            System.out.print(i + "\t");
            if((i + start) % 7 == 0) System.out.println("");
        }
    }
}