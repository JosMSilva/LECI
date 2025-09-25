package ExameP;

public class CaravanSpace  extends CampingSpace{
    public CaravanSpace(String location, int[] dimensions, double pricePerNight){
        super(location, dimensions, pricePerNight, SpaceType.CARAVAN);
    }
}
