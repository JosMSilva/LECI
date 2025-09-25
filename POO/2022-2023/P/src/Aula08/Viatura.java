package Aula08;

public  abstract class Viatura implements KmPercorridosInterface {

    private int kmPercorridos;
    private int ultimoTrajeto;
    private int potencia;
    private String matricula, marca, modelo;
    
    public Viatura(String matricula, String marca, String modelo, int potencia){

        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.potencia = potencia;

        kmPercorridos = 0;
        ultimoTrajeto = 0;
    }
    
    @Override
    public void trajeto(int km) {
        kmPercorridos += km;
        ultimoTrajeto = km;
    }

    @Override
    public void ultimoTrajeto() {
        System.out.printf("O último trajeto foi de %d km%n",ultimoTrajeto);
    }

    @Override
    public void distanciaTotal() {
        System.out.printf("A distância total percorrida é de %d km%n",kmPercorridos);
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
}
