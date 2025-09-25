package Aula11;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;

public class Gradebook {

    public Gradebook() {
    }

    public void load(String alunos) {
        try {
            File myObj = new File(alunos);
            Scanner fileReader = new Scanner(myObj,"UTF-8");
            fileReader.useDelimiter("[|\n]+");
            while (fileReader.hasNext()) {
              String name = fileReader.next();
              List<Double> grades = new ArrayList<>(List.of(fileReader.nextDouble(),fileReader.nextDouble(),fileReader.nextDouble())); {
                Student newStudent = new Student(name, grades);
                students.add(newStudent);
                System.out.println(students);
              };
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found");
            e.printStackTrace();
        }

    }

    public void addStudent(Student newStudent) {
    }
    

    private HashSet<Student> students = new HashSet<Student>();
}
