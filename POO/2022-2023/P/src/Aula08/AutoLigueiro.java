package Aula08;

public class AutoLigueiro extends Viatura {
        
        private int nQuadro, cMaxima;
        
        public AutoLigueiro(String matricula, String marca, String modelo, int potencia,int nQuadro, int cMaxima){
            super(matricula, marca, modelo, potencia);
            this.nQuadro = nQuadro;
            this.cMaxima = cMaxima;
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
        
        public void setCMaxima(int cMaxima){
            this.cMaxima = cMaxima;
        }
        
        public int getCMaxima(){
            return cMaxima;
        }

        public int getnQuadro() {
            return nQuadro;
        }

        public void setnQuadro(int nQuadro) {
            this.nQuadro = nQuadro;
        }

        
        @Override public String toString(){
            return String.format("Matricula: %s, Marca: %s, Modelo: %s, Potencia: %3d, Numero do Quadro: %d, Capcidade Máxima: %3d kg ",getMatricula(), getMarca(), getModelo(), getPotencia(),getnQuadro(), getCMaxima());
        }
    
}
