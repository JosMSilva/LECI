package Aula09;

public class CommercialPlane extends Plane {
    public CommercialPlane(String ID, String Maker, String Model, int Year, int pCapacity, int Speed, int tCapacity) {
        super(ID, Maker, Model, Year, pCapacity, Speed);
        this.tCapacity = tCapacity;
    }

    public int gettCapacity() {
        return tCapacity;
    }

    public void settCapacity(int tCapacity) {
        this.tCapacity = tCapacity;
    }

    @Override
    public String getPlaneType() {
        return "Commercial";
    }

    private int tCapacity;

    
    @Override
    public String toString() {
        return " Commercial Plane [ID=" + getID() + ", Maker=" + getMaker() + ", Model=" + getModel() + ", Year=" + getYear() + ", People="
                + getpCapacity() + ", Speed=" + getSpeed() + ", Crew =" + gettCapacity() + "]";
    }
    
}
