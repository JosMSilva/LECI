package Aula10;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Ex04 {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(new FileReader("major.txt"));
        while (input.hasNext()) {
        String word = input.next();
        HashSet<String> words = new HashSet<String>();
        if(word.length() > 1) words.add(word);
        if(word.endsWith("s"))System.out.println(word);
        for (String string : words) {
            if(!string.matches("\\w"))words.remove(string);
        }
        }
        }
}
