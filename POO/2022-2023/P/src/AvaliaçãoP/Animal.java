package AvaliaçãoP;

public class Animal {
    private static int count = 1;
    private final int id;

    public Animal(String Name, double Weight, int Age, String Sponsor){
        assert Name != null : "Nome inválido";
        assert Weight > 0 : "Peso inválido";
        assert Age > 0 : "Idade inválida";
        this.id = count++;
        this.Name = Name;
        this.Weight = Weight;
        this.Age = Age;
        this.Sponsor = Sponsor;

    }

    public int getId(){
        return id;
    }   

    public String getName(){
        return Name;
    }

    public double getWeight(){
        return Weight;
    }

    public int getAge(){
        return Age;
    }

    public String getSponsor(){
        return Sponsor;
    }

    public void setName(String Name){
        assert Name != null : "Nome inválido";
        this.Name = Name;
    }

    public void setWeight(double Weight){
        assert Weight > 0 : "Peso inválido";
        this.Weight = Weight;
    }

    public void setAge(int Age){
        assert Age > 0 : "Idade inválida";
        this.Age = Age;
    }

    public void setSponsor(String Sponsor){
        this.Sponsor = Sponsor;
    }

    public boolean equals(Animal animal){
        return (this.id == animal.id) && (this.Name.equals(animal.Name)) && (this.Weight == animal.Weight) && (this.Age == animal.Age) && (this.Sponsor.equals(animal.Sponsor));
    }

    @Override public String toString(){
        return String.format("Nome: %s, Peso: %.2f, Idade: %3d, Padrinho: %s",Name, Weight, Age, Sponsor);
    }


    private String Name;
    private double Weight;
    private int Age;
    private String Sponsor;
    
}
