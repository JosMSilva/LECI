package AvaliaçãoP;

public class Bird extends Animal {
    private final String habitat;
    
    public Bird(String name, double weight, int age, String habitat, String sponsor) {
      super(name, weight, age, sponsor);
      this.habitat = habitat;
    }

    public String getHabitat() {
        return habitat;
    }

    public boolean equals(Bird bird){
        return (this.getId() == bird.getId()) && (this.getName().equals(bird.getName())) && (this.getWeight() == bird.getWeight()) && (this.getAge() == bird.getAge()) && (this.getSponsor().equals(bird.getSponsor())) && (this.getHabitat().equals(bird.getHabitat()));
    }

    @Override public String toString(){
        return String.format("Nome: %s, Peso: %.2f, Idade: %3d, Habitat: %s, Padrinho: %s ",getName(), getWeight(), getAge(), getHabitat(), getSponsor());
    }
  }
