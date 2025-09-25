package Aula08;

public class PesadoMerc extends Pesado{
        
        private int cargaMaxima;
        
        public PesadoMerc(String matricula, String marca, String modelo, int potencia, int nQuadro, int cMaxima, int peso, int cargaMaxima){
            super(matricula, marca, modelo, potencia, nQuadro, cMaxima, peso);
            this.cargaMaxima = cargaMaxima;
        }
        
        public void trajeto(int km){
            super.trajeto(km);
        }
        
        public void ultimoTrajeto(){
            super.ultimoTrajeto();
        }
        
        public void distanciaTotal(){
            super.distanciaTotal();
        }
        
        public void setCargaMaxima(int cargaMaxima){
            this.cargaMaxima = cargaMaxima;
        }
        
        public int getCargaMaxima(){
            return cargaMaxima;
        }
        
        @Override public String toString(){
            return String.format("Matricula: %s, Marca: %s, Modelo: %s, Potencia: %3d, Nº Quadro: %3d, Carga Maxima: %3d, Peso: %3d, Carga Maxima: %3d",getMatricula(), getMarca(), getModelo(), getPotencia(), getnQuadro(), getCMaxima(), getPeso(), getCargaMaxima());
        }
    
    
}
