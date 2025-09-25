package Aula12;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Ex02 {
    public static void main(String[] args) {
        try {
            File myObj = new File("movies.txt");
            Scanner fileReader = new Scanner(myObj,"utf-8");
            fileReader.useDelimiter("[\t\n]");
            List<Movie> Movies = new ArrayList<Movie>();
            String palavra = fileReader.next();
            Movie myMovie = new Movie(palavra, fileReader.next(), fileReader.next(), fileReader.next(), fileReader.next());
            int i = 1;
            while(fileReader.hasNext()){
                palavra = fileReader.next();
                myMovie(i) = new Movie(palavra, fileReader.next(), fileReader.next(), fileReader.next(), fileReader.next());
                Movies.add(myMovie);
                
            }
            Collections.sort(Movies);
            System.out.println(Movies);
            
            
            fileReader.close();




        }catch (FileNotFoundException e) {
            System.err.println("File Not Found");
            e.printStackTrace();
        }

    }
    interface MoviesCompareScore{

    }
    
}
