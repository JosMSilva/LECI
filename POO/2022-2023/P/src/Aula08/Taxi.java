package Aula08;

public class Taxi extends AutoLigueiro {
        
        private int nTaxi;
        
        public Taxi(String matricula, String marca, String modelo, int potencia, int nQuadro, int cMaxima, int nTaxi){
            super(matricula, marca, modelo, potencia, nQuadro, cMaxima);
            this.nTaxi = nTaxi;
        }
        
        public void setNTaxi(int nTaxi){
            this.nTaxi = nTaxi;
        }
        
        public int getNTaxi(){
            return nTaxi;
        }
        
        @Override public String toString(){
            return String.format("Matricula: %s, Marca: %s, Modelo: %s, Potencia: %3d, Numero do Quadro: %d, Capcidade Máxima: %3d kg, Numero do Taxi: %d",getMatricula(), getMarca(), getModelo(), getPotencia(),getnQuadro(), getCMaxima(), getNTaxi());
        }
    
}
