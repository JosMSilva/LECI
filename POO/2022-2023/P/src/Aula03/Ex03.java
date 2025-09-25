package Aula03;
import java.util.Scanner;
import java.util.Random;

public class Ex03 {

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        int random = rand.nextInt(100);
        int guess;
        int guessnum = 0;
        boolean continueGame = true;
        do{
            System.out.print("Insira um número entre 0 e 100:");
            guess = sc.nextInt();
            guessnum++;
            if(guess < random){
                System.out.println("O número é maior");
            }else if(guess > random){
                System.out.println("O número é menor");
            }else{
                System.out.printf("Acertou em %d tentativas!%n",guessnum);
                System.out.println("Prima S para continuar");
                if(sc.next().equalsIgnoreCase("s")){
                    continueGame = true;
                    random = rand.nextInt(100);
                    guessnum = 0;
                }else{
                    continueGame = false;
                }
            }
        }while(continueGame);
        sc.close();
    }


}
