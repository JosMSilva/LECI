package Aula07;

public class Forma {

    public Forma(String color){
        this.color = color;
    }

    public String color(){
        return color;
    }

    private String color;



    @Override public boolean equals(Object o) {
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        /*if((o.getClass()).getName() == "java.lang.Circulo"){
            if(this.rad != o.rad || this.color != o.color) return false;
            return true;
        }else if((o.getClass()).getName() == "java.lang.Retangulo"){
            if(this.alt != o.alt || this.comp != o.comp || this.color != o.color) return false;
            return true;
        }else{
            if(this.lad1 != o.lad1 || this.lad2 != o.lad2 || this.lad3 != o.lad3 || this.color != o.color) return false;
            return true;
            
        }*/

        if(this == o){
            return true;
        }else{
            return false;
        }

        

    }
    
}
