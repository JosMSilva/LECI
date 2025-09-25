import java.time.LocalDate;

public class Transporte extends Servico {


    private Combustivel combustivel;
    private int ocupantes;
    private int km;

    public Transporte(int ocupantes,int km, Combustivel combustivel) {
        super(part, 22);
        this.ocupantes = ocupantes;
        this.km = km;
        this.combustivel = combustivel;
    }


    public int getOcupantes() {
        return this.ocupantes;
    }

    public void setOcupantes(int ocupantes) {
        this.ocupantes = ocupantes;
    }

    public int getKm() {
        return this.km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public Combustivel getCombustivel() {
        return this.combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }


    
    
}