package Aula12;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

public class Ex03 {
    static TreeMap<String, Movie> movies = new TreeMap<>();
    public static void main(String[] args) {
        try {
            File myObj = new File("movies.txt");
            Scanner fileReader = new Scanner(myObj,"UTF-8");
            fileReader.useDelimiter("[\t\n]+");
            String data = fileReader.next();
            while(!data.equals("running time"))data = fileReader.next();
            while (fileReader.hasNext()) {
                String name = fileReader.next();
                double score = Double.parseDouble(fileReader.next());
                String rating = fileReader.next();
                String genre = fileReader.next();
                int runTime = Integer.parseInt(fileReader.next());
                Movie movie = new Movie(name,score,rating,genre,runTime);
                movies.put(name,movie);
            }
            fileReader.close();
            System.out.println(scoreOrder());
            System.out.println(timeOrder());
            createFile();
            try {
                FileWriter myWriter = new FileWriter("List.txt");
                myWriter.write("\n");
                myWriter.write("All Movies: \n");
                myWriter.write("\n");
                for(Movie movie : movies.values()){
                    myWriter.write(movie.toString());
                    myWriter.write("\n");
                }
                myWriter.write("\n");
                myWriter.write("All Movies by Score: \n");
                myWriter.write("\n");
                for(Movie movie : scoreOrder().values()){
                    myWriter.write(movie.toString());
                    myWriter.write("\n");
                }
                myWriter.write("\n");
                myWriter.write("All Movies by Run Time: \n");
                myWriter.write("\n");
                for(Movie movie : timeOrder().values()){
                    myWriter.write(movie.toString());
                    myWriter.write("\n");
                }
                myWriter.write("\n");
                myWriter.write("Personal Selection: \n");
                myWriter.write("\n");
                for(Movie movie : movies.values()){
                    if(movie.getScore() >= 60){
                        myWriter.write(movie.toString());
                        myWriter.write("\n");
                    }
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }



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

    public static TreeMap<Double, Movie> scoreOrder(){
        TreeMap<Double, Movie> scoreMovies = new TreeMap<>();
        for(Movie movie : movies.values()){
            scoreMovies.put(movie.getScore(),movie);
        }
        return scoreMovies;
    }

    public static TreeMap<Integer, Movie> timeOrder(){
        TreeMap<Integer, Movie> timeMovies = new TreeMap<>();
        for(Movie movie : movies.values()){
            timeMovies.put(movie.getRunTime(),movie);
        }
        return timeMovies;
    }
}
