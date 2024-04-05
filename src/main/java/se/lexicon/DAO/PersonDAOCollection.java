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
                throw new IllegalArgumentException("Person already exist");
            }
        }
        persons.add(person);
        return person;
    }

    @Override
    public Person findById(Integer id) {
        if (id < 0) {
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
        if (email == null || !email.contains("@")) {
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
    public void remove(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        persons.removeIf(person -> person.getId()== id);

    }
}
