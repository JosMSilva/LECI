package Aula11;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Ex01 {
    public static void main(String[] args) {
        TreeMap<String, ArrayList<String>> dict = new TreeMap<String, ArrayList<String>>();
        try {
          File myObj = new File("major.txt");
          Scanner fileReader = new Scanner(myObj,"utf-8");
          fileReader.useDelimiter("[\\p{Punct} \t\n\r]+");
          String data = fileReader.next();
          int i;
          int j = 1;
          while (fileReader.hasNext()) {
            String temp = fileReader.next();
            data = data.toLowerCase();
            temp = temp.toLowerCase();
            if (data.length() > 2 && temp.length() > 2){
                if(dict.containsKey(data) && dict.get(data).indexOf(temp)  == -1){
                    dict.get(data).add(temp + "=1");
                }else if(!dict.containsKey(data)){
                    dict.put(data, new ArrayList<String>());
                    dict.get(data).add(temp + "=1");
                }else if(dict.get(data).indexOf(temp)  != -1){
                  do{
                    i = dict.get(data).indexOf(temp);
                    dict.get(data).remove(i);
                    j++;

                  }while(dict.get(data).indexOf(temp) != dict.get(data).lastIndexOf(temp));
                  dict.get(data).add(temp + "=" + j);
                  dict.get(data).remove(temp + "=1");

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
