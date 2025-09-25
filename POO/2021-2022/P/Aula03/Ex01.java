package Aula03;
import java.util.Scanner;

public class Ex01 {
        static Scanner sc = new Scanner(System.in);
    
        public static void main (String[] args){
            boolean start = true;
            System.out.print("Introduza a nota teorica: ");
            double temp = sc.nextDouble();
            double notaT = val(start,temp);
            System.out.print("Introduza a nota prática: ");
            temp = sc.nextDouble();
            start = false;
            double notaP = val(start,temp);
 
            
             if(notaP < 7 || notaT < 7)
             {
                 System.out.println("66 (reprovado por nota minima)");
             }else{
                 double media = (notaT*0.4)+(notaP*0.6);
                 System.out.printf("A média é %.0f",media);
             }
            
            
        }

        public static double val (boolean start,double nota){
            do{
            if(nota < 0 || nota > 20 ){
                System.err.println("Nota não válida");
            }
            if(start){
                System.out.print("Introduza a nota teorica: ");
                nota = sc.nextDouble();
            }else{
                System.out.print("Introduza a nota prática: ");
                nota = sc.nextDouble(); 
            }
    
            }while(nota < 0 || nota > 20);
            nota = nota*10;
            Math.round(nota);
            nota = nota/10;
            return nota;
        }
        
    }

