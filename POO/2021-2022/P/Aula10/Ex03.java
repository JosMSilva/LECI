package Aula10;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Ex03 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        HashMap<Character, ArrayList<Integer>> letters = new HashMap<Character, ArrayList<Integer>>();
        String forn = sc.nextLine();
        letters.put(forn.charAt(0), new ArrayList<Integer>());
        letters.get(forn.charAt(0)).add(0);
        for(int i = 1; i < forn.length(); i++){
            for(char k: letters.keySet()){
                if(k == forn.charAt(i)){
                    letters.get(forn.charAt(i)).add(i);
                }else{
                    letters.put(forn.charAt(i), new ArrayList<Integer>()); 
                    letters.get(forn.charAt(i)).add(i);
                }
            }

        }
       
        System.out.println(letters.toString());

    }
}
