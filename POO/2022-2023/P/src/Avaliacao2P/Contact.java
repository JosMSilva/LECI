package Avaliacao2P;

import java.time.LocalDate;

public class Contact {

    private static int idCounter = 0;

    public Contact(String name, int phoneNumber, String email, LocalDate birthDate) {
        
        this.id = ++idCounter;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;

    }

    public static int getIdCounter() {
        return idCounter;
    }
    public static void setIdCounter(int idCounter) {
        Contact.idCounter = idCounter;
    }
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    private int id;
    private String name;
    private int phoneNumber;
    private String email;
    private LocalDate birthDate;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + phoneNumber;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contact other = (Contact) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (phoneNumber != other.phoneNumber)
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Contact " + id + ": Name=" + name + ", Phone Number=" + phoneNumber + ", Email=" + email
                + ", Birth Date=" + birthDate;
    }

}

