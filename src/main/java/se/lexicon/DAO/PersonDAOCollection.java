package se.lexicon.DAO;

import se.lexicon.Model.Person;

import java.util.ArrayList;
import java.util.Collection;


public class PersonDAOCollection implements IPersonDAO {

    private final ArrayList<Person> persons;

    public PersonDAOCollection() {
        this.persons = new ArrayList<>();
    }

    @Override
    public Person persist(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        for (Person existingPerson : persons) {
            if (existingPerson.getId() == person.getId()) {
                throw new IllegalArgumentException("Person with id: " + person.getId() + " already exists");
            }
        }
        persons.add(person);
        return person;
    }

    @Override
    public Person findById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }

        for (Person existingPerson : persons) {
            if (existingPerson.getId() == id) {
                return existingPerson;
            }
        }
        return null;
    }

    @Override
    public Person findByEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (email == null || !email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        for (Person existingEmail : persons) {
            if (existingEmail.getEmail().equalsIgnoreCase(email)) {
                return existingEmail;
            }
        }
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        return new ArrayList<>(persons);
    }

    @Override
    public void remove(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        persons.removeIf(person -> person.getId() == id);

    }
}
