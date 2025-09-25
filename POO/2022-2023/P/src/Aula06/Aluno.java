package Aula06;
import java.time.LocalDateTime;   

public class Aluno extends Pessoa {
    public Aluno(String nome, int cc, Date dataNasc, Date dataInsc) {
        super(nome, cc, dataNasc);
        LocalDateTime now = LocalDateTime.now();
        if (dataInsc == null) dataInsc = new Date(now.getDayOfMonth(), now.getMonthValue(), now.getYear());
        this.dataInsc = dataInsc;
    }


    public Date dataInsc() {
        return dataInsc;
    }

    private Date dataInsc;

    @Override public String toString(){
        return String.format("Nome: %s, Cartão de Cidadão: %08d, Data de Nascimento: %s, Data Inscrição: %s",nome(), cc(), dataNasc().toString(), dataInsc().toString());
    }
}
