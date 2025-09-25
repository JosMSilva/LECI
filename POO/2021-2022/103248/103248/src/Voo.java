import java.time.LocalDate;

public class Voo extends Servico {


    private Classe classe;
    private String cod;
    private LocalDate data;

    public Voo(String cod,LocalDate data, Classe classe) {
        super(part, 22);
        this.cod = cod;
        this.data = data;
        this.classe = classe;
    }


    public Classe getClasse() {
        return this.classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public String getCod() {
        return this.cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        

        Voo other = (Voo) obj;
        return this.getData().equals(other.getData()) && this.getCod().equals(other.getCod()) && this.getClasse().equals(other.getClasse());
    }

    @Override
    public String toString() {
        return String.format("\t Voo %s às %s na classe %s.", this.getCod(), this.getData(),getClasse());
    }
    
}