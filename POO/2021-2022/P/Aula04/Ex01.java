package Aula04;
import java.util.Scanner;

public class Ex01 {
        static Scanner sc = new Scanner(System.in);
        public static void main (String[] args){
            System.out.print("Introduza a frase: ");
            String frase = sc.nextLine();
            if(!frase.isEmpty()){
                System.out.println(frase.toLowerCase());
                System.out.println(frase.charAt((frase.length())-1));
                if(frase.length() >= 3){
                    System.out.println(frase.substring(0, 3));
                }else{
                    System.out.println("String tem menos de 3 caracteres");
                }
                System.out.println(frase.toUpperCase());
                System.out.println(frase.isEmpty());
                System.out.println(frase.length());
            }else{
                System.out.println("String está vazia");
            }
            
        }
        
    }
