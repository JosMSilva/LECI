package AvaliaçãoP;

public class PetShelter implements IPetShelter {
    public PetShelter(String name){
        assert name != null : "Nome inválido";
        this.name = name;
    }


    @Override
    public void addAnimal(Animal animal) {
       
        
    }

    @Override
    public void removeAnimal(Animal animal) {
        
    }

    @Override
    public Animal searchForAnimal(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                return animal;
            }
            
        }
        return null;
        
    }

    @Override
    public boolean sponsorAnimal(int animalId) {
        for (Animal animal : animals) {
            if (animal.getId() == animalId) {
                animal.setSponsor("Sponsored");
                return true;
            }
        }
        return false;
        
    }

    @Override
    public void listAllAnimals() {
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
        
    }

    private String name;

    private Animal[] animals;
}
