package Aula09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Ex01 {
        public static void main(String[] args) {
            ArrayList<Integer> c1 = new ArrayList<>();
            for (int i = 10; i <= 100; i+=10) 
            c1.add(i);
            System.out.println("Size: " + c1.size());
            for (int i = 0; i < c1.size(); i++) 
            System.out.println("Elemento: " + c1.get(i));
            ArrayList<String> c2 = new ArrayList<>();
            c2.add("Vento");
            c2.add("Calor");
            c2.add("Frio");
            c2.add("Chuva");
            System.out.println(c2);
            Collections.sort(c2);
            System.out.println(c2);
            c2.remove("Frio"); 
            c2.remove(0);
            System.out.println(c2);

            HashSet<Pessoa> c3 = new HashSet<>();
            c3.add(new Pessoa("João", 12345678, new Date(1, 1, 2000)));
            c3.add(new Pessoa("Maria", 87654321, new Date(1, 12, 2000)));
            c3.add(new Pessoa("Ana", 92445638, new Date(5, 8, 2004)));
            c3.add(new Pessoa("Manel", 42545988, new Date(3, 4, 1999)));
            c3.add(new Pessoa("Joaquina", 45852446, new Date(28, 1, 2006)));

            Iterator<Pessoa> it = c3.iterator();

            while (it.hasNext()) {
                System.out.println(it.next());
            }

    }
}