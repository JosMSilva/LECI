package Aula06;

public class Pessoa {
    private String nome;
    private int cc;
    private Date dataNasc;

    public Pessoa(String nome, int cc, Date dataNasc){
        assert nomeCheck(nome);
        assert ccCheck(cc);
        set(nome,cc,dataNasc);
    }
    public void set(String nome, int cc, Date dataNasc){
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

    @Override public String toString(){
        return String.format("%s; CC: %08d; Data de Nascimento: %s",nome,cc,dataNasc);
    }

    public static boolean nomeCheck(String nome){
        assert !(nome.isEmpty());
        for(int i = 0; i < nome.length(); i++){
            if(Character.isDigit(nome.charAt(i)))return false;
        }
        return true;
    }

    public static boolean ccCheck(int cc){
        return (cc >= 0 && cc <= 99999999);
    }
}
