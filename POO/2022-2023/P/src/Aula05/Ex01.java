package Aula05;
import java.util.Scanner;

public class Ex01 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int funtion = -1;
        Date d = null;
        do{
            System.out.println("1 - Inserir Nova Data");
            System.out.println("2 - Mostrar Data");
            System.out.println("3 - Incrementar Data");
            System.out.println("4 - Decrementar Data");
            System.out.println("0 - Sair");

            funtion = sc.nextInt();

            switch(funtion){
                case 1:
                    System.out.print("Insira a data (dd/mm/aaaa):");
                    String data = sc.next();
                    String[] date = data.split("/");
                    assert date.length == 3 : "Formato inválido";
                    int day = Integer.parseInt(date[0]);
                    int month = Integer.parseInt(date[1]);
                    int year = Integer.parseInt(date[2]);
                    d = new Date(day, month, year);

                    Calendar c = new Calendar(1, 2022);
                    c.addEvent(d);
                    c.printMonth(month);
                    break;

                case 2:
                    assert d != null : "Data não inserida";
                    System.out.println(d.toString());
                    break;

                case 3:
                    assert d != null : "Data não inserida";
                    d.increment();
                    break;

                case 4:
                    assert d != null : "Data não inserida";
                    d.decrement();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }



        }while(funtion != 0);
    }
    
}
