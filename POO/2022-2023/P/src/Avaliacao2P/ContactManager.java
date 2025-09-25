package Avaliacao2P;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

public class ContactManager implements ContactManagerInterface {

    private HashSet<Contact> contacts = new HashSet<>();


     @Override
    public void load(String filePath) {
        try{
            File myObj = new File(filePath);
            Scanner fileReader = new Scanner(myObj,"UTF-8");
            fileReader.useDelimiter("[\t\n]+");
            while (fileReader.hasNext()) {
                String name = fileReader.next();
                String tNumber = fileReader.next();
                int pnumber = Integer.parseInt(tNumber);
                String email = fileReader.next();
                LocalDate dataN = LocalDate.parse(fileReader.next());
                assert validateEmail(email) && validatePhoneNumber(tNumber) : "Invalid email or phone number";
                Contact nContact = new Contact(name,pnumber,email,dataN);
                contacts.add(nContact);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found");
            e.printStackTrace();
        }
    }
    
    @Override
    public void save(String filePath) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            for(Contact contact : contacts){
                myWriter.write(contact.getName() + "\t");
                myWriter.write(contact.getPhoneNumber() + "\t");
                myWriter.write(contact.getEmail() + "\t");
                myWriter.write(contact.getBirthDate() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    
    @Override
    public boolean validateEmail(String email) {
        if(email.endsWith("@ua.pt")){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        if(phoneNumber.length() == 9){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean addContact(Contact person) {
        contacts.add(person);
        return true;
    }
    
    @Override
    public boolean removeContact(Contact person) {
        for(Contact contact : contacts){
            if(contact.equals(person)){
                contacts.remove(contact);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Contact searchContactByname(String name) {
        for(Contact contact : contacts){
            if(contact.getName().equals(name)){
                return contact;
            }
        }
        return null;
    }
    
    @Override
    public Contact searchContactByEmail(String email) {
        for(Contact contact : contacts){
            if(contact.getEmail().equals(email)){
                return contact;
            }
        }
        return null;
    }
    
    @Override
    public Contact searchContactByPhoneNumber(int phoneNumber) {
        for(Contact contact : contacts){
            if(contact.getPhoneNumber() == phoneNumber){
                return contact;
            }
        }
        return null;
    }
    
    @Override
    public void listAllContacts() {
        for(Contact contact : contacts){
            System.out.println(contact.toString());
        }
    
    }
    
    @Override
    public void listContactsByName() {
        TreeMap<String,Contact> contactMap = new TreeMap<>();
        for(Contact contact : contacts){
            contactMap.put(contact.getName(),contact);
        }
        for(Contact contact : contactMap.values()){
            System.out.println(contact.toString());
        }
    
    }
    
    @Override
    public void listContactsByPhoneNumber() {
        TreeMap<Integer,Contact> contactMap = new TreeMap<>();
        for(Contact contact : contacts){
            contactMap.put(contact.getPhoneNumber(),contact);
        }
        for(Contact contact : contactMap.values()){
            System.out.println(contact.toString());
        }
    
    }
    
    @Override
    public void listContactsByBirthDate() {
        TreeMap<LocalDate,Contact> contactMap = new TreeMap<>();
        for(Contact contact : contacts){
            contactMap.put(contact.getBirthDate(),contact);
        }
        for(Contact contact : contactMap.values()){
            System.out.println(contact.toString());
        }
    
    }
    
}
