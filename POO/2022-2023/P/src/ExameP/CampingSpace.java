package ExameP;

import java.util.Arrays;

public class CampingSpace {
    public CampingSpace(String location, int[] dimensions, double pricePerNight, SpaceType type){
        this.location = location;
        this.dimensions = dimensions;
        this.pricePerNight = pricePerNight;
        this.type = type;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int[] getDimensions() {
        return dimensions;
    }
    public void setDimensions(int[] dimensions) {
        this.dimensions = dimensions;
    }
    public double getPricePerNight() {
        return pricePerNight;
    }
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
    public SpaceType getType() {
        return type;
    }

    private String location;
    private int[] dimensions;
    private double pricePerNight;
    private SpaceType type;
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + Arrays.hashCode(dimensions);
        long temp;
        temp = Double.doubleToLongBits(pricePerNight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CampingSpace other = (CampingSpace) obj;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (!Arrays.equals(dimensions, other.dimensions))
            return false;
        if (Double.doubleToLongBits(pricePerNight) != Double.doubleToLongBits(other.pricePerNight))
            return false;
        if (type != other.type)
            return false;
        return true;
    }
}
