package Aula08;

import java.util.ArrayList;

public class Empresa {
    public Empresa(String Nome){
        this.Nome = Nome;
    }

    public void addViatura(Viatura v){
        listaViaturas.add(v);
    }

    public void removeViatura(Viatura v){
        listaViaturas.remove(v);
    }

    public void addTrajeto(String Matricula, int km){
        for(Viatura v : listaViaturas){
            if(v.getMatricula().equals(Matricula)){
                v.trajeto(km);
            }
        }
    }

    public void ultimoTrajeto(String Matricula){
        for(Viatura v : listaViaturas){
            if(v.getMatricula().equals(Matricula)){
                v.ultimoTrajeto();
            }
        }
    }
    
    
    private ArrayList<Viatura> listaViaturas = new ArrayList<>();
    private String Nome;
    
}
