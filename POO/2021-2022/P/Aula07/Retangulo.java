package Aula07;

public class Retangulo extends Forma{
    
    public Retangulo(String color, int comp, int alt){
        super(color);
        assert valid(comp, alt);
        set(comp, alt);
    }

    public static boolean validLad(int lad){
        return lad >= 0;
    }


    public static boolean valid(int comp, int alt){
        return validLad(comp) && validLad(alt);
    }
    public void set(int comp, int alt){
        assert valid(comp, alt);
        this.comp = comp;
        this.alt = alt;

    }
    public int comp(){
        return comp;

    }
    public int alt(){
        return alt;
    }
   
    
    private int comp, alt; 
}
