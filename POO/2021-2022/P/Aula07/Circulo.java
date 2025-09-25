package Aula07;

public class Circulo extends Forma{

    
    public Circulo(String color, int rad){
        super(color);
        assert validRad(rad);
        set(rad);
    }

    public static boolean validRad(int rad){
        return rad >= 0;
    }

    public void set(int rad){
        assert validRad(rad);
        this.rad = rad;

    }
    public int rad(){
        return rad;

    }
   
    
    private int rad; 
}

