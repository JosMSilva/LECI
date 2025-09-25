package Aula06;

public class Bolseiro extends Aluno {
    public Bolseiro(String nome, int cc, Date dataNasc, Date dataInsc, Professor responsavel, int valorBolsa) {
        super(nome, cc, dataNasc, dataInsc);
        assert responsavel != null : "Responsável inválido";
        assert valorBolsa > 0 : "Valor da bolsa inválido";
        this.responsavel = responsavel;
        this.valorBolsa = valorBolsa;
    }

    public void setResponsavel(Professor responsavel) {
        assert responsavel != null : "Responsável inválido";
        this.responsavel = responsavel;
    }

    public void setValorBolsa(int valorBolsa) {
        assert valorBolsa > 0 : "Valor da bolsa inválido";
        this.valorBolsa = valorBolsa;
    }

    public Professor responsavel() {
        return responsavel;
    }
    public int valorBolsa() {
        return valorBolsa;
    }

    private Professor responsavel;
    private int valorBolsa;

    @Override public String toString(){
        return String.format("Nome: %s, Cartão de Cidadão: %08d, Data de Nascimento: %s, Data Inscrição: %s, Professor Responsavel: %s, Valor da Bolsa %d",nome(), cc(), dataNasc().toString(), dataInsc().toString(), responsavel().nome(), valorBolsa());
    }
    
}
