package Aula11;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.TreeMap;

public class Ex01 {
    public static void main(String[] args) {
        TreeMap<String, TreeMap<String, Integer>> dict = new TreeMap<>();
        try {
          File myObj = new File("major.txt");
          Scanner fileReader = new Scanner(myObj,"UTF-8");
          fileReader.useDelimiter("[‘’“”\\p{Punct} \t\n\r]+");
          String data = fileReader.next();
          while (fileReader.hasNext()) {
            String temp = fileReader.next().trim();
            data = data.toLowerCase();
            temp = temp.toLowerCase();
            if (data.length() > 2 && temp.length() > 2){
                if(dict.containsKey(data) && dict.get(data).containsKey(temp)){
                    dict.get(data).put(temp,dict.get(data).get(temp)+1);
                }else if(!dict.containsKey(data)){
                    dict.put(data, new TreeMap<String, Integer>());
                    dict.get(data).put(temp,1);
                }else{
                    dict.get(data).put(temp,1);
                }

            } 
            data = temp;
            
          }
          System.out.println(dict);
          fileReader.close();
        } catch (FileNotFoundException e) {
          System.err.println("File Not Found");
          e.printStackTrace();
        }
      }
}
