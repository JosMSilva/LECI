package Aula04;
import java.util.Scanner;

public class Ex03 {
    
    static Scanner sc = new Scanner(System.in);
        public static void main (String[] args){
            System.out.print("Introduza a frase: ");
            String frase = sc.nextLine();
            if(!frase.isEmpty()){
                frase = frase.toUpperCase();
                System.out.println(Acro(frase));
               
            }else{
                System.out.println("String está vazia");
            }
            
        }

        public static String Acro(String frase){
            char temp = frase.charAt(0);
            String acro = String.valueOf(temp);
            for(int i = 1; i < frase.length(); i++){
                if(frase.charAt(i-1) == ' ' && frase.charAt(i+1) != ' ' && frase.charAt(i+2) != ' '&& frase.charAt(i+3) != ' '){
                    acro += frase.charAt(i);
                }
            }

            return acro;

        }
        
    }

