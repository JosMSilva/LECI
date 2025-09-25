import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class PacoteTuristico implements IPacoteTuristico {

    private int dias;
    private String nome;
    private double preço;
    private Set<Servico> servicos;

    public PacoteTuristico(String Nome, int dias, double preço) {
        this.nome = nome;
        this.dias = dias;
        this.preço = preço;
    }

    public String getNome() { return this.nome; }
    public double getPreço() { return this.preço; }
    public int getDias() { return this.dias; }
    public void setPreco(double preço){
        this.preço = preço;
    }

    public Servico adicionaServico(String CodI, String CodF) {
        
        Servico servico = new Servico(CodI, CodF);
        return servico;
    }

    public int precoTotal(PacoteTuristico p, int pessoa) {
        int total = 0;
        double totalI = p.getPreço() * pessoa;
        total = (int) totalI;
        return total;
    }

    
    
}
