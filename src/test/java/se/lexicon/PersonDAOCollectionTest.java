package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.data.impl.PersonDAOCollection;
import se.lexicon.model.Person;
import se.lexicon.exception.EntityAlreadyExistsException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOCollectionTest {
    private PersonDAOCollection testObject;
    private Person testPerson;

    @BeforeEach
    void setUp() {
        testObject = new PersonDAOCollection();
        testPerson = new Person();
        testPerson.setFirstName("Test");
        testPerson.setLastName("Testsson");
        testPerson.setEmail("test@test.se");
    }

    @Test
    public void persist_ShouldPersistPerson() {
        Person persistedPerson = testObject.persist(testPerson);
        assertEquals(testPerson, persistedPerson);
        assertTrue(testObject.find(testPerson.getId()).isPresent());
    }

    @Test
    public void persist_ShouldThrowException_WhenPersonAlreadyExists() {
        testObject.persist(testPerson);
        assertThrows(EntityAlreadyExistsException.class, () -> testObject.persist(testPerson));
    }

    @Test
    public void find_ShouldReturnPerson_WhenPersonExists() {
        testObject.persist(testPerson);
        Optional<Person> foundPerson = testObject.find(testPerson.getId());
        assertTrue(foundPerson.isPresent());
        assertEquals(testPerson, foundPerson.get());
    }

    @Test
    public void find_ShouldReturnEmptyOptional_WhenPersonDoesNotExist() {
        Optional<Person> foundPerson = testObject.find(999);
        assertFalse(foundPerson.isPresent());
    }

    @Test
    public void remove_ShouldRemovePerson_WhenPersonExists() {
        testObject.persist(testPerson);
        Optional<Person> removedPerson = testObject.remove(testPerson.getId());
        assertTrue(removedPerson.isPresent());
        assertEquals(testPerson, removedPerson.get());
        assertFalse(testObject.find(testPerson.getId()).isPresent());
    }

    @Test
    public void remove_ShouldReturnEmptyOptional_WhenPersonDoesNotExist() {
        Optional<Person> removedPerson = testObject.remove(999);
        assertFalse(removedPerson.isPresent());
    }
}