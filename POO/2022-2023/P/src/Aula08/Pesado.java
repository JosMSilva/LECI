package Aula08;

public abstract class Pesado extends AutoLigueiro {
        
        
    public Pesado(String matricula, String marca, String modelo, int potencia, int nQuadro, int cMaxima, int peso){
        super(matricula, marca, modelo, potencia, nQuadro, cMaxima);
        this.peso = peso;
    }

    private int peso;

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    
    
}
