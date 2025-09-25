package Aula08;

public class Motociclo extends Viatura {
    
        private String tipo;
        
        public Motociclo(String matricula, String marca, String modelo, int potencia, String tipo){
            super(matricula, marca, modelo, potencia);
            this.tipo = tipo;
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
        
        

        public void setTipo(String tipo){
            this.tipo = tipo;
        }

        public String getTipo(){
            return tipo;
        }

        @Override public String toString(){
            return String.format("Matricula: %s, Marca: %s, Modelo: %s, Potencia: %3d, Tipo: %s ",getMatricula(), getMarca(), getModelo(), getPotencia(), getTipo());
        }
    
}
