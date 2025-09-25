package AvaliaçãoP;

public class Dog extends Animal {
    private final String breed;
    
    public Dog(String name, double weight, int age, String breed, String sponsor) {
      super(name, weight, age, sponsor);
      this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public boolean equals(Dog dog){
        return (this.getId() == dog.getId()) && (this.getName().equals(dog.getName())) && (this.getWeight() == dog.getWeight()) && (this.getAge() == dog.getAge()) && (this.getSponsor().equals(dog.getSponsor())) && (this.getBreed().equals(dog.getBreed()));
    }

    @Override public String toString(){
        return String.format("Nome: %s, Peso: %.2f, Idade: %3d, Raça: %s, Padrinho: %s ",getName(), getWeight(), getAge(), getSponsor(), getBreed());
    }
  }

