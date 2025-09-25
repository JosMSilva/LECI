package Aula12;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Ex01 {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(new FileReader("A_cidade_e_as_serras.txt"));
        input.useDelimiter("[‘’“”\\p{Punct} \t\n\r]+");
        int wordCount = 0;
        HashSet<String> words = new HashSet<String>();
        while (input.hasNext()) {
        String word = input.next();
        word = word.toLowerCase();
        words.add(word);
        wordCount++;
        System.out.println(word);
        }
        System.out.println("Total words = " + wordCount);
        System.out.println("Different words = " + words.size());
        }
}
