package se.lexicon.data.impl;

import se.lexicon.data.IPersonDAO;
import se.lexicon.data.sequencers.PersonIdSequencer;
import se.lexicon.exception.EntityAlreadyExistsException;
import se.lexicon.model.Person;

import java.util.*;

/**
 * This class implements the IPersonDAO interface and provides concrete implementations for each of the operations that can be performed on Person objects.
 */
public class PersonDAOCollection implements IPersonDAO {

    /**
     * A list of Person objects. This list is used to store all the Person instances managed by this DAO.
     */
    private final Map<Integer,Person> persons = new HashMap<>();

    /**
     * Method to persist a Person object.
     * @param person The Person object to be persisted.
     * @return The persisted Person object.
     * @throws IllegalArgumentException If the input Person is null or already exists in the list.
     */
    @Override
    public Person persist(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        Optional<Person> personOptional = find(person.getId());
        if (personOptional.isPresent()) throw new EntityAlreadyExistsException("Person already exist");

        int id = PersonIdSequencer.nextId();
        person.setId(id);
        persons.put(id,person);
        return person;
    }

    /**
     * Method to find a Person by id.
     * @param id The id of the Person to be found.
     * @return An Optional containing the found Person, or an empty Optional if no Person was found.
     */
    @Override
    public Optional<Person> find(int id) {
        return Optional.ofNullable(persons.get(id));
    }

    /**
     * Method to find a Person by their email.
     * @param email The email to search for.
     * @return An Optional containing the found Person, or null if no Person was found.
     * @throws IllegalArgumentException If the email is null or does not match the required format.
     */
    @Override
    public Optional<Person> find(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (email == null || !email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        for (Person existingEmail : persons.values()) {
            if (existingEmail.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(existingEmail);
            }
        }
        return Optional.empty();
    }

    /**
     * Method to find all Person objects.
     * @return A Collection containing all Person objects.
     */
    @Override
    public Collection<Person> find() {
        return new ArrayList<>(persons.values());
    }

    /**
     * Method to remove a Person by id.
     * @param id The id of the Person to be removed.
     * @throws IllegalArgumentException If the Person is not found.
     */
    @Override
    public Optional<Person> remove(int id) {
       return Optional.ofNullable(persons.remove(id));
    }
}