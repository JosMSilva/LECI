package Aula07;

public class Triangulo extends Forma{
    
    public Triangulo(String color, int lad1, int lad2, int lad3){
        super(color);
        assert valid(lad1, lad2, lad3);
        set(lad1, lad2, lad3);
    }

    public static boolean validLad(int lad){
        return lad >= 0;
    }

    public static boolean validTrig(int lad1, int lad2, int lad3){
        return (lad1 < lad2 + lad3) && (lad2 < lad1 + lad3) && (lad3 < lad1 + lad2);
    }


    public static boolean valid(int lad1, int lad2, int lad3){
        return validLad(lad1) && validLad(lad2) && validLad(lad3) && validTrig(lad1, lad2, lad3);
    }
    public void set(int lad1, int lad2, int lad3){
        assert valid(lad1, lad2, lad3);
        this.lad1 = lad1;
        this.lad2 = lad2;
        this.lad3 = lad3;

    }
    public int lad1(){
        return lad1;

    }
    public int lad2(){
        return lad2;
    }
    public int lad3(){
        return lad3;
    }
   
    
    
    private int lad1, lad2, lad3; 
}
