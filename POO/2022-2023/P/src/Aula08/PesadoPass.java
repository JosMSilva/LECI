package Aula08;

public class PesadoPass extends Pesado{
        
        private int nPessoas;
        
        public PesadoPass(String matricula, String marca, String modelo, int potencia, int nQuadro, int cMaxima, int peso, int nPessoas){
            super(matricula, marca, modelo, potencia, nQuadro, cMaxima, peso);
            this.nPessoas = nPessoas;
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
        
        public void setNPessoas(int nPessoas){
            this.nPessoas = nPessoas;
        }
        
        public int getNPessoas(){
            return nPessoas;
        }
        
        @Override public String toString(){
            return String.format("Matricula: %s, Marca: %s, Modelo: %s, Potencia: %3d, Nº Quadro: %3d, Carga Maxima: %3d, Peso: %3d, Carga Maxima: %3d",getMatricula(), getMarca(), getModelo(), getPotencia(), getnQuadro(), getCMaxima(), getPeso(), getNPessoas());
        }
    
    
}
