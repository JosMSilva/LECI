package AvaliaçãoP;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IPetShelter shelter = new PetShelter("Patudos");

        int choice = 0;
        do {
            System.out.println("Choose an option:");
            System.out.println("1. Add animal");
            System.out.println("2. Remove animal");
            System.out.println("3. Search for animal");
            System.out.println("4. Sponsor an animal");
            System.out.println("5. View all animals");
            System.out.println("6. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                System.out.println("Choose the type of animal:");
                String animalType = scanner.nextLine();
                Animal newAnimal = null;
                System.out.println("Name:");
                String Name = scanner.nextLine();
                System.out.println("Weight:");
                double Weight = scanner.nextDouble();
                System.out.println("Age:");
                int Age = scanner.nextInt();
                System.out.println("Sponsor:");
                String Sponsor = scanner.nextLine();

                
                switch (animalType){
                    case "Bird":
                        System.out.println("Habitat:");
                        String Habitat = scanner.nextLine();
                        newAnimal = new Bird(Name, Weight, Age, Sponsor, Habitat);
                        break;
                    case "Rabbit":
                        System.out.println("Fur:");
                        String Fur = scanner.nextLine();
                        newAnimal = new Rabbit(Name, Weight, Age, Sponsor, Fur);
                        break;
                    case "Dog":
                        System.out.println("Breed:");
                        String Breed = scanner.nextLine();
                        newAnimal = new Dog(Name, Weight, Age, Sponsor, Breed);
                        break;  
                    default:
                        System.out.println("Tipo de animal inválido");
                        break;
                }
		            shelter.addAnimal(newAnimal);
                case 2:
                    shelter.removeAnimal(null);
                case 3:
                    shelter.searchForAnimal(null);
                case 4:
                    shelter.sponsorAnimal(0);
                case 5:
                    shelter.listAllAnimals();
                case 6:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }

        } while (choice != 6);
        scanner.close();
    }
}

