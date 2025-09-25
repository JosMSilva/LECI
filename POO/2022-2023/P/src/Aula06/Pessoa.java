package Aula06;

public class Pessoa {
    public Pessoa (String nome, int cc, Date dataNasc){
        assert nome != null : "Nome inválido";
        assert cc > 0 && cc < 99999999: "CC inválido";
        this.nome = nome;
        this.cc = cc;
        this.dataNasc = dataNasc;
    }

    public String nome(){
        return nome;
    }

    public int cc(){
        return cc;
    }

    public Date dataNasc(){
        return dataNasc;
    }

    private String nome;
    private int cc;
    private Date dataNasc;

    @Override public String toString(){
        return String.format("Nome: %s, Cartão de Cidadão: %08d, Data de Nascimento: %s",nome, cc, dataNasc.toString());
    }
}

