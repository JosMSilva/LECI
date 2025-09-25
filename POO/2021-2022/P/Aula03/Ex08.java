package Aula03;
import java.util.Scanner;

public class Ex08 {
        static Scanner sc = new Scanner(System.in);
    
        public static void main (String[] args){
            double temp;
            double[][] notas = new double[16][3];
            for(int i = 0; i < 16; i++){
                System.out.print("Introduza a nota teorica: ");
                temp = sc.nextDouble();
                notas[i][0] = val(temp);
                System.out.print("Introduza a nota prática: ");
                temp = sc.nextDouble();
                notas[i][1] = val(temp);
                if(notas[i][0] < 7 || notas[i][1] < 7){
                    notas[i][2] = 66;
                }else{
                    notas[i][2] = (notas[i][0]*0.4)+(notas[i][1]*0.6);
                }
            }
            for(int i = 0; i < 16; i++){
                System.out.println("NotaT  NotaP  Pauta");
                System.out.printf("%.1f  %.1f  %.1f\n",notas[i][0],notas[i][1],notas[i][2]);
            }
 
            
             
            
            
        }

        public static double val (double nota){
            do{
            if(nota < 0 || nota > 20 ){
                System.err.print("Nota não válida, tente novamente: ");
                nota = sc.nextDouble();
            }
            }while(nota < 0 || nota > 20);
            nota = nota*10;
            Math.round(nota);
            nota = nota/10;
            return nota;
        }
        
    }

