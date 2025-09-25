package Aula03;
import java.util.Scanner;
import java.util.Random;

public class Ex04 {
    
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        System.out.print("Insira o número de alunos:");
        int alunos = sc.nextInt(); 
        while(alunos < 0){
            System.out.print("Valor inválido, insira novamente:");
            alunos = sc.nextInt();
        }
        double[][] notas = new double[alunos][2];
        for (int i = 0; i < notas.length; i++) {
            notas[i][0] = rand.nextDouble(0.0, 20.0);
            notas[i][1] = rand.nextDouble(0.0, 20.0);
        }

        System.out.println("Aluno\tNota T\tNota P\tMédia");
        for (int i = 0; i < notas.length; i++) {
            double media = (notas[i][0]*0.4 + (notas[i][1] * 0.6));
            if(notas[i][0] < 7.0 || notas[i][1] < 7.0) media = 66;
            System.out.printf("  %d\t%2.1f\t%2.1f\t%2.0f %n",i+1,notas[i][0],notas[i][1],media);
        }
        
        sc.close();
    }
}
