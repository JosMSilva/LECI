import java.time.LocalDate;

public class Alojamento extends Servico {


    private Regime regime;
    private String hotel;
    private int quartos;
    private int max;

    public Alojamento(String hotel,int quartos,int max, Regime regime) {
        super(participants, 22);
        this.max = max;
        this.hotel = hotel;
        this.regime = regime;
        this.quartos = quartos;
    }


    public Regime getRegime() {
        return this.regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

    public String getHotel() {
        return this.hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    public int getQuartos() {
        return this.quartos;
    }

    public void setQuartos(int quartos) {
        this.quartos = quartos;
    }


    
}