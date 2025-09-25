package ExameP;

public class Client {

    public Client(int NIF, String name, ClientType type){
        this.NIF = NIF;
        this.name = name;
        this.type = type;
    }
    
    public int getNIF() {
        return NIF;
    }
    public void setNIF(int nIF) {
        NIF = nIF;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ClientType getType() {
        return type;
    }
    public void setType(ClientType type) {
        this.type = type;
    }

    private int NIF;
    private String name;
    private ClientType type;

    @Override
    public String toString() {
        return name + " [" + type + ": " + NIF + "]";
    }
    }
