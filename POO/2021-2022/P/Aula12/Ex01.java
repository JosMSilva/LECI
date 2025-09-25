package Aula12;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Ex01 {
    public static void main(String[] args) {
        try {
            File myObj = new File("major.txt");
            Scanner fileReader = new Scanner(myObj,"utf-8");
            fileReader.useDelimiter("[\\p{Punct} \t\n\r]+");
            String palavra = fileReader.next();
            ArrayList<String> back = new ArrayList<String>();
            back.add(palavra);
            int plt = 1;
            int pld = 1;
            do{
                palavra = fileReader.next();
                plt++;
                if(!back.contains(palavra))pld++;
                back.add(palavra);
                

            }while(fileReader.hasNext());
            System.out.println(plt);
            System.out.println(pld);
            fileReader.close();




        }catch (FileNotFoundException e) {
            System.err.println("File Not Found");
            e.printStackTrace();
        }
    }
    
}
