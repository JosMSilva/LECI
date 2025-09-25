public abstract class Servico {
    
    private int CodF;
    private String CodI;
    

    public Servico(String CodI, int CodF) {
        this.CodI = CodI;
        this.CodF = CodF;
    }

    public String getCodI() {
        return this.CodI;
    }

    public void setCodI(String CodI) {
        this.CodI = CodI;
    }

    public int getCodF() {
        return this.CodF;
    }

    public void setCodF(int CodF) {
        this.CodF = CodF;
    }

    
}
