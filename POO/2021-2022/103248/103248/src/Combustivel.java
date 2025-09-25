
public enum Combustivel {
    GASOLINA("Gasolina"), GASOLEO("Gasoleo"), HIBRIDO("Hibrido"),ELETRICO("Eletrico");

    private String friendlyName;

    Combustivel(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    @Override
    public String toString() {
        return this.friendlyName;
    }

}
