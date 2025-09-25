package Aula09;

public class MilitaryPlane extends Plane {
    public MilitaryPlane(String ID, String Maker, String Model, int Year, int pCapacity, int Speed, int mCapacity) {
        super(ID, Maker, Model, Year, pCapacity, Speed);
        this.mCapacity = mCapacity;
    }

    public int getmCapacity() {
        return mCapacity;
    }

    public void setmCapacity(int mCapacity) {
        this.mCapacity = mCapacity;
    }

    @Override
    public String getPlaneType() {
        return "Military";
    }

    private int mCapacity;

    
    @Override
    public String toString() {
        return " Military Plane [ID=" + getID() + ", Maker=" + getMaker() + ", Model=" + getModel() + ", Year=" + getYear() + ", People="
                + getpCapacity() + ", Speed=" + getSpeed() + ", Ammuniton =" + getmCapacity() + "]";
    }
    
}
