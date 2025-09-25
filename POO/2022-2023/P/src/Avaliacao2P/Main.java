package Avaliacao2P;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

    int choose;
    do{
        ContactManager contactManager = new ContactManager();
        sc.useDelimiter("[\t\n\r]+");
        contactManager.load("src\\Avaliacao2P\\contactos.txt");
        System.out.println("1 - Add Contact");
        System.out.println("2 - Modify Contact");
        System.out.println("3 - Remove Contact");
        System.out.println("4 - Search Contact by name");
        System.out.println("5 - Search Contact by phone number");
        System.out.println("6 - Search Contact by email");
        System.out.println("7 - List all contacts");
        System.out.println("8 - List contacts by name");
        System.out.println("9 - List contacts by phone number");
        System.out.println("10 - List contacts by birth date");
        System.out.println("0 - Close");
        choose = sc.nextInt();
        
        switch(choose){
            case 1:
                System.out.print("Name: ");
                String name = sc.next();
                System.out.print("Phone Number: ");
                String tNumber = sc.next();
                int pnumber = Integer.parseInt(tNumber);
                System.out.print("Email: ");
                String email = sc.next();
                System.out.print("Birth Date: ");
                LocalDate dataN = LocalDate.parse(sc.next());
                assert contactManager.validateEmail(email) && contactManager.validatePhoneNumber(tNumber) : "Invalid email or phone number";
                Contact nContact = new Contact(name,pnumber,email,dataN);
                contactManager.addContact(nContact);
                break;
            case 2:
                System.out.print("Which contact do you want to modify? ");
                contactManager.listAllContacts();
                String name2 = sc.next();
                Contact contact = contactManager.searchContactByname(name2);
                System.out.println("1 - Modify Name");
                System.out.println("2 - Modify Phone Number");
                System.out.println("3 - Modify Email");
                System.out.println("4 - Modify Birth Date");
                int choose2 = sc.nextInt();
                switch(choose2){
                    case 1:
                        System.out.print("New Name: ");
                        String newName = sc.next();
                        contact.setName(newName);
                        break;
                    case 2:
                        System.out.print("New Phone Number: ");
                        String newTNumber = sc.next();
                        int newPnumber = Integer.parseInt(newTNumber);
                        assert contactManager.validatePhoneNumber(newTNumber) : "Invalid phone number";
                        contact.setPhoneNumber(newPnumber);
                        break;
                    case 3:
                        System.out.print("New Email: ");
                        String newEmail = sc.next();
                        assert contactManager.validateEmail(newEmail) : "Invalid email";
                        contact.setEmail(newEmail);
                        break;
                    case 4:
                        System.out.print("New Birth Date: ");
                        LocalDate newDataN = LocalDate.parse(sc.next());
                        contact.setBirthDate(newDataN);
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
                break;
            case 3:
                System.out.print("Which contact do you want to remove? ");
                contactManager.listAllContacts();
                String name3 = sc.next();
                Contact contact2 = contactManager.searchContactByname(name3);
                contactManager.removeContact(contact2);
                break;
            case 4:
                System.out.print("Name: ");
                String name4 = sc.next();
                Contact contact3 = contactManager.searchContactByname(name4);
                System.out.println(contact3);
                break;
            case 5:
                System.out.print("Phone Number: ");
                int number = sc.nextInt();
                Contact contact4 = contactManager.searchContactByPhoneNumber(number);
                System.out.println(contact4);
                break;
            case 6:
                System.out.print("Email: ");
                String name5 = sc.next();
                Contact contact5 = contactManager.searchContactByEmail(name5);
                System.out.println(contact5);
                break;
            case 7:
                contactManager.listAllContacts();
                break;
            case 8:
                contactManager.listContactsByName();
                break;
            case 9:
                contactManager.listContactsByPhoneNumber();
                break;
            case 10:
                contactManager.listContactsByBirthDate();
                break;
            case 0:
                contactManager.save("src\\Avaliacao2P\\contactos.txt");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }while(choose != 0);
    }
    
}
