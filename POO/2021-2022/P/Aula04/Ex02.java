package Aula04;
import java.util.Scanner;


public class Ex02 {
        static Scanner sc = new Scanner(System.in);
        public static void main (String[] args){
            System.out.print("Introduza a frase: ");
            String frase = sc.nextLine();
            if(!frase.isEmpty()){
                System.out.println(countDigit(frase));
                System.out.println(countspace(frase));
                if(checkMax(frase)){
                    System.out.println("Frase está em maiusculas");
                }else{
                    System.out.println("Frase não está em maiusculas");
                }
                System.out.println(removeSpace(frase));
                if(palindro(frase)){
                    System.out.println("Frase é um palindro");
                }else{
                    System.out.println("Frase não é um palindro");
                }
            }else{
                System.out.println("Frase Vazia");
            }
        }

        public static int countDigit(String frase){
            int a = 0;
            for(int i = 0; i < frase.length(); i++){
                char temp = frase.charAt(i);
                switch(temp){
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9': a++; break;
                    default: break;
                }
            }
            return a;
        }

        public static int countspace(String frase){
            int a = 0;
            for(int i = 0; i < frase.length(); i++){
                char temp = frase.charAt(i);
                if(temp == ' ') a++;
            }
            return a;
        }

        public static boolean checkMax(String frase){
            boolean f = false;
            if(frase == frase.toUpperCase()) f = true;
            return f;
        }
        public static String removeSpace(String frase){
            char[] newC = new char[frase.length()];
            int a = 1;
            newC[0] = frase.charAt(0);
            for(int i = 1; i < frase.length(); i++){
                if((frase.charAt(i) == frase.charAt(i-1))&&(frase.charAt(i) == ' ')){

                }else{
                    newC[a++] = frase.charAt(i);

                }
            }
            return String.valueOf(newC);
            
        }

       /* public static boolean palindro(String frase){
            boolean val = false;
            char[] newC = new char[frase.length()];
            int a = 0;
            for(int i = frase.length()-1; i >= 0; i--){
                
                    newC[a++] = frase.charAt(i);

            }
            String inv = String.valueOf(newC);
            System.out.println("Inverso é " + inv);
            if(inv.equalsIgnoreCase(frase))val = true;

            return val;

            
        }*/
        public static boolean palindro(String frase){
            boolean val = false;
            int a = frase.length()-1;
            for(int i = 0; i <= frase.length()/2; i++){
                if(frase.charAt(i) != frase.charAt(a--))return val;
            }
            val = true;
            return val;

            
        }
        
    }
