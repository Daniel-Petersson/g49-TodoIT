package se.lexicon.model;

import java.util.Objects;

public class Person {
    //Fields
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private AppUser credentials;

    //Constructors

    //Setters

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("Firstname cannot be null or empty");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Lastname cannot be null or empty");
        }
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.email = email;
    }

    public void setCredentials(AppUser credentials) {
        this.credentials = credentials;
    }
//Getters

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public AppUser getCredentials() {
        return credentials;
    }

    //Methods
    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Person { " + "id: " + id + ", Name: " + getName() + ", Email: " + email + "}";

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(email, person.email);
    }
}
