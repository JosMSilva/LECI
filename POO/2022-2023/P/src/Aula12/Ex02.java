package Aula12;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

public class Ex02 {
    public static void main(String[] args) {
        TreeMap<Character, TreeMap<String, Integer>> dict = new TreeMap<>();
        try {
          File myObj = new File("A_cidade_e_as_serras.txt");
          Scanner fileReader = new Scanner(myObj,"UTF-8");
          fileReader.useDelimiter("[‘’“”\\p{Punct} \t\n\r]+");
          while (fileReader.hasNext()) {
            String data = fileReader.next();
            data = data.toLowerCase();
            if (data.length() > 2 && data.matches("^[a-zA-Z]*$")){
                if(dict.containsKey(data.charAt(0)) && dict.get(data.charAt(0)).containsKey(data)){
                    dict.get(data.charAt(0)).put(data,dict.get(data.charAt(0)).get(data)+1);
                }else if(!dict.containsKey(data.charAt(0))){
                    dict.put(data.charAt(0), new TreeMap<String, Integer>());
                    dict.get(data.charAt(0)).put(data,1);
                }else{
                    dict.get(data.charAt(0)).put(data,1);
                }

            }
          }
          System.out.println(dict);
          createFile();
          try {
            FileWriter myWriter = new FileWriter("List.txt");
            myWriter.write(dict.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          fileReader.close();



        } catch (FileNotFoundException e) {
          System.err.println("File Not Found");
          e.printStackTrace();
        }
    }

    public static void createFile(){
        try {
            File myObj = new File("List.txt");
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
            }
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}
