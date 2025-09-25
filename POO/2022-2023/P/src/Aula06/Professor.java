package Aula06;

public class Professor extends Pessoa{
    public Professor(String nome, int cc, Date dataNasc, String Categoria, String Departamento) {
        super(nome, cc, dataNasc);
        assert Categoria == "Auxiliar" || Categoria == "Associado" || Categoria == "Catedrático" : "Categoria inválida";
        this.Categoria = Categoria;
        this.Departamento = Departamento;
    }

    public String Categoria() {
        return Categoria;
    }

    public String Departamento() {
        return Departamento;
    }

    private String Categoria, Departamento;

    @Override public String toString(){
        return String.format("Nome: %s, Cartão de Cidadão: %08d, Data de Nascimento: %s, Categoria: %s, Departamento: ",nome(), cc(), dataNasc().toString(), Categoria(), Departamento());
    }
    
}
