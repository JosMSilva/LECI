package Aula06;

public class Aluno extends Pessoa {

    private int numMec;
    private Date dataInsc;

    public Aluno(String nome, int cc, Date dataNasc, Date dataInsc){
        super(nome, cc, dataNasc);
        this.dataInsc = dataInsc;
        numMec += 1;
        
    }


    public int numMec(){
        return numMec;

    }
    public Date dataInsc(){
        return dataInsc;
    }


    
}
