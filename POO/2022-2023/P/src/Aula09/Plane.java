package Aula09;

public class Plane {
    public Plane(String ID, String Maker, String Model, int Year, int pCapacity, int Speed) {
        this.ID = ID;
        this.Maker = Maker;
        this.Model = Model;
        this.Year = Year;
        this.pCapacity = pCapacity;
        this.Speed = Speed;
    }

    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getMaker() {
        return Maker;
    }
    public void setMaker(String maker) {
        Maker = maker;
    }
    public String getModel() {
        return Model;
    }
    public void setModel(String model) {
        Model = model;
    }
    public int getYear() {
        return Year;
    }
    public void setYear(int year) {
        Year = year;
    }
    public int getpCapacity() {
        return pCapacity;
    }
    public void setpCapacity(int pCapacity) {
        this.pCapacity = pCapacity;
    }
    public int getSpeed() {
        return Speed;
    }
    public void setSpeed(int speed) {
        Speed = speed;
    }

    public String getPlaneType() {
        return " Basic Plane";
    }

    private String ID, Maker, Model;
    private int Year, pCapacity, Speed;

    @Override
    public String toString() {
        return "Plane [ID=" + ID + ", Maker=" + Maker + ", Model=" + Model + ", Year=" + Year + ", pCapacity="
                + pCapacity + ", Speed=" + Speed + "]";
    }
    
}
