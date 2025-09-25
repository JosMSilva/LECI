package Aula06;
import java.util.Scanner;

public class Ex01 {
    static Scanner sc = new Scanner(System.in);

    public static void main (String[] args){
        String nome;
        int cc, day, month, year;
        System.out.print("Introduza o nome:");
                nome = sc.nextLine();
                System.out.print("Introduza o cc:");
                cc = sc.nextInt();
                System.out.print("Introduza o dia:");
                day = sc.nextInt();
                System.out.print("Introduza o mês:");
                month = sc.nextInt();
                System.out.print("Introduza o ano:");
                year = sc.nextInt();
                Date data = new Date(day, month, year);
                Pessoa pessoa = new Pessoa(nome, cc, data);
                System.out.println(pessoa.toString());
                Aluno al = new Aluno("Jose", 30327244, data, data);
                Aluno al1 = new Aluno("Jose", 30327244, data, data);
                Aluno al2 = new Aluno("Jose", 30327244, data, data);
                System.out.print(al.numMec());
                System.out.print(al1.numMec());
                System.out.print(al2.numMec());
    
    }
}
