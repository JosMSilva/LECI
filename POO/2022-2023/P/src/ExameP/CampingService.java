package ExameP;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.HashMap;

public class CampingService implements CampingServiceInterface{

    public CampingService(String name, String address) {
        this.name = name;
        this.address = address;
        this.clients = new ArrayList<>();
        this.campingSpaces = new ArrayList<>();
        this.bookings = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public boolean registerClient(int taxId, String name, ClientType type) {
        Client client = new Client(taxId, name, type);
        clients.add(client);
        return true;
    }

    @Override
    public Client getClient(int taxId) {
        for(Client client : clients){
            if(client.getNIF() == taxId){
                return client;
            }
        }
        return null;
    }

    @Override
    public void addCampingSpace(CampingSpace campingSpace) {
        campingSpaces.add(campingSpace);
    }

    @Override
    public void addCampingSpaces(Collection<CampingSpace> campingSpaces) {
        this.campingSpaces.addAll(campingSpaces);
    }

    @Override
    public boolean checkAvailable(CampingSpace campingSpace, LocalDate startDate, LocalDate endDate) {
        if(!bookings.containsKey(campingSpace)){
            return true;
        }else{
            for(LocalDate date : bookings.get(campingSpace).keySet()){
                if(date.isAfter(startDate) && date.isBefore(endDate)){
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public List<CampingSpace> findAvailableCampingSpaces(SpaceType spaceType, LocalDate fromDate, int duration,
            int[] minDimensions) {
        List<CampingSpace> freeCampingSpaces = new ArrayList<CampingSpace>();
        for( CampingSpace booking : bookings.keySet()){
            if(booking.getType() == spaceType && checkAvailable(booking, fromDate, fromDate.plusDays(duration))){
                int[] dimensions = booking.getDimensions();
                if(dimensions[0] >= minDimensions[0] && dimensions[1] >= minDimensions[1]){
                    freeCampingSpaces.add(booking);
                }
            }
        }
        return freeCampingSpaces;
    }

    @Override
    public boolean bookCampingSpace(Client client, CampingSpace campingSpace, LocalDate startDate, int duration) {
        if(!checkAvailable(campingSpace, startDate, startDate.plusDays(duration))){
            return false;
        }else if(client.getType() == ClientType.NORMAL && campingSpace.getType() == SpaceType.CARAVAN){
            return false;
        }else{
            bookings.put(campingSpace, new HashMap<LocalDate, HashMap<Client, Integer>>());
            bookings.get(campingSpace).put(startDate, new HashMap<Client, Integer>());
            bookings.get(campingSpace).get(startDate).put(client, duration);
            return true;
        }
    }

    @Override
    public double calculateTotalCost(CampingSpace campingSpace, int duration) {
        return campingSpace.getPricePerNight()  * duration;
    }

    @Override
    public List<String> listBookings() {
        List<String> bookingsList = new ArrayList<String>();
        for(CampingSpace campingSpace : bookings.keySet()){
            for(LocalDate date : bookings.get(campingSpace).keySet()){
                for(Client client : bookings.get(campingSpace).get(date).keySet()){
                    bookingsList.add(client.toString() + " - " + "[" + date.toString() + "]"+ campingSpace.toString() + " " + campingSpace.getLocation().toString() +  " ");
                }
            }
        }
        return bookingsList;
    }

    @Override
    public List<String> listBookings(SpaceType spaceType) {
        List<String> bookingsList = new ArrayList<String>();
        for(CampingSpace campingSpace : bookings.keySet()){
            if(campingSpace.getType() == spaceType){
                for(LocalDate date : bookings.get(campingSpace).keySet()){
                    for(Client client : bookings.get(campingSpace).get(date).keySet()){
                        bookingsList.add(campingSpace.toString() + " " + date.toString() + " " + client.toString() + " " + bookings.get(campingSpace).get(date).get(client));
                    }
                }
            }
        }
        return bookingsList;
    }

    public List<String> getAvailableSpacesByTotalArea(LocalDate starDate, int duration) {
        List<String> availableSpaces = new ArrayList<String>();
        for(CampingSpace campingSpace : campingSpaces){
            if(checkAvailable(campingSpace, starDate, starDate.plusDays(duration))){
                availableSpaces.add(campingSpace.toString());
            }
        }

        return availableSpaces;
    }

    private String name;
    private String address;

    private List<Client> clients;
    private List<CampingSpace> campingSpaces;
    private HashMap<CampingSpace, HashMap<LocalDate, HashMap<Client, Integer>>> bookings;
    
}
    
