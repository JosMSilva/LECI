package AvaliaçãoP;

public class Rabbit extends Animal {

    private final String Fur;

    public Rabbit(String Name, double Weight, int Age, String Fur, String Sponsor) {
        super(Name, Weight, Age, Sponsor);
        this.Fur = Fur;
    }

    public String getFur() {
        return Fur;
    }

    public boolean equals(Rabbit rabbit) {
        return (this.getId() == rabbit.getId()) && (this.getName().equals(rabbit.getName())) && (this.getWeight() == rabbit.getWeight()) && (this.getAge() == rabbit.getAge()) && (this.getSponsor().equals(rabbit.getSponsor())) && (this.getFur().equals(rabbit.getFur()));
    }

    @Override
    public String toString() {
        return String.format("Nome: %s, Peso: %.2f, Idade: %3d, Pelo: %s, Padrinho: %s ", getName(), getWeight(), getAge(),getFur(), getSponsor());
    }


    
}
