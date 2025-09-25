package Aula09;

import java.util.ArrayList;
import java.util.TreeMap;

public class PlaneManager {

    public PlaneManager(){

    }

    public void addPlane(Plane p){
        planes.put(p.getID(), p);
    }

    public void removePlane(String ID){
        planes.remove(ID);
    }

    public Plane searchPlane(String ID){
        return planes.get(ID);
    }

    public ArrayList<Plane> getCommercialPlanes(){
        ArrayList<Plane> commercialPlanes = new ArrayList<Plane>();
        for(Plane p : planes.values()){
            if(p.getPlaneType().equals("Commercial")){
                commercialPlanes.add(p);
            }
        }
        return commercialPlanes;
    }

    public ArrayList<Plane> getMilitaryPlanes(){
        ArrayList<Plane> militaryPlanes = new ArrayList<Plane>();
        for(Plane p : planes.values()){
            if(p.getPlaneType().equals("Military")){
                militaryPlanes.add(p);
            }
        }
        return militaryPlanes;
    }

    public ArrayList<Plane> getAllPlanes(){
        ArrayList<Plane> allPlanes = new ArrayList<Plane>();
        for(Plane p : planes.values()){
            allPlanes.add(p);
        }
        return allPlanes;
    }

    public Plane getFasterPlane(){
        Plane faster = null;
        for(Plane p : planes.values()){
            if(faster == null || p.getSpeed() > faster.getSpeed()){
                faster = p;
            }
        }
        return faster;
    }

    private TreeMap<String, Plane> planes = new TreeMap<>();
    
}
