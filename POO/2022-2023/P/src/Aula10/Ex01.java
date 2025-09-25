package Aula10;
import java.util.*;

public class Ex01 {

    static Scanner sc = new Scanner(System.in);
    static TreeMap<String, HashSet<Book>> Books = new TreeMap<String, HashSet<Book>>();
    public static void main(String[] args) {
        HashSet<Book> DBooks = new HashSet<Book>();
        HashSet<Book> FBooks = new HashSet<Book>();
        HashSet<Book> ABooks = new HashSet<Book>();
        HashSet<Book> TBooks = new HashSet<Book>();
        HashSet<Book> RBooks = new HashSet<Book>();
        Books.put("Drama", DBooks);
        Books.put("Fiction", FBooks);
        Books.put("Adventure", ABooks);
        Books.put("Thriller", TBooks);
        Books.put("Romance", RBooks);

        Book b1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        Book b2 = new Book("The Catcher in the Rye", "J. D. Salinger", 1951);
        
        DBooks.add(b1);

        FBooks.add(b2);

        System.out.println(Books);
            
    }

    @Override
        public String toString() {
            return "Ex01{" + "Books=" + Books + '}';
            
        }
}