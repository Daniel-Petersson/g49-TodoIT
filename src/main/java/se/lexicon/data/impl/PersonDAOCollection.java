package se.lexicon.data.impl;

import se.lexicon.data.IPersonDAO;
import se.lexicon.data.sequencers.PersonIdSequencer;
import se.lexicon.exception.EntityAlreadyExistsException;
import se.lexicon.model.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class implements the IPersonDAO interface and provides concrete implementations for each of the operations that can be performed on Person objects.
 */
public class PersonDAOCollection implements IPersonDAO {

    /**
     * A list of Person objects. This list is used to store all the Person instances managed by this DAO.
     */
    private final Map<Integer, Person> persons = new HashMap<>();

    @Override
    public Person persist(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        Optional<Person> personOptional = find(person.getId());
        if (personOptional.isPresent()) throw new EntityAlreadyExistsException("Person already exist");

        int id = PersonIdSequencer.nextId();
        person.setId(id);
        persons.put(id, person);
        return person;
    }

    @Override
    public Optional<Person> find(int id) {
        return Optional.ofNullable(persons.get(id));
    }

    @Override
    public Optional<Person> find(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (email == null || !email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        return persons.values().stream()
                .filter(person -> person.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public Collection<Person> find() {
        return persons.values();
    }

    @Override
    public Optional<Person> remove(int id) {
        return Optional.ofNullable(persons.remove(id));
    }
}