public enum Classe {
    TURISTICA("Turistica"), EXCLUSIVA("Exclusiva"), PRIMEIRA("Primeira");

    private String friendlyName;

    Classe(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    @Override
    public String toString() {
        return this.friendlyName;
    }
}