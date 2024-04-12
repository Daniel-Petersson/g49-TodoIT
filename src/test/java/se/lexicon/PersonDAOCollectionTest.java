package se.lexicon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.data.impl.PersonDAOCollection;
import se.lexicon.exception.EntityAlreadyExistsException;
import se.lexicon.model.Person;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOCollectionTest {

    private PersonDAOCollection testObject;
    private Person person; // Declare person as a field
    private Person person1;

    @BeforeEach
    public void setUp() {
        // Initialize testObject
        testObject = new PersonDAOCollection();
        // Initialize some Person instances for testing
        person = new Person();
        person.setFirstName("Test");
        person.setLastName("Testsson");
        person.setEmail("Test@test.se");

        person1 = new Person();
        person1.setFirstName("Tset");
        person1.setLastName("Testsson");



    }
    // Test the persist method
    @Test
    public void testPersist() {
        // Test if a new Person can be persisted successfully
        Person actualValue = testObject.persist(person);
        Person expectedValue = person;
        // Assert
        // Check if create method creates customer
        assertEquals(expectedValue, actualValue);
        // Test if EntityAlreadyExistsException is thrown when trying to persist a Person that already exists
        assertThrows(EntityAlreadyExistsException.class, () -> testObject.persist(person));

    }

    @Test
    void testFind_byId() {
        // Add person to testObject
        testObject.persist(person);

        // Call the find method on testObject with the id of person
        Optional<Person> foundPerson = testObject.find(person.getId());


        assertTrue(foundPerson.isPresent());

        // Assert that the returned Person is the same as the person that was added
        assertEquals(person, foundPerson.get());
    }

    @Test
    void testFind_byEmail() {
        // Add Person can be found by email
        testObject.persist(person);
        // Call find with email
        Optional<Person> noEmail = testObject.find("no@email.no");
        // Test if an empty Optional is returned when trying to find a Person with an email that does not exist
        assertFalse(noEmail.isPresent());

        // Test if a Person can be found by email
        Optional<Person> email = testObject.find(person.getEmail());
        assertTrue(email.isPresent());
        assertEquals(person, email.get());

        // Test if IllegalArgumentException is thrown when trying to find a Person with an invalid email format
        assertThrows(IllegalArgumentException.class, () -> testObject.find("invalid email format"));
    }

    // Test the find method
    @Test
    void testFind_allPersons() {
        // Arrange
        Person person2 = new Person();
        Person person3 = new Person();
        testObject.persist(person2);
        testObject.persist(person3);

        // Act
        Collection<Person> result = testObject.find();
        // Assert
        assertEquals(2,result.size());
        assertTrue(result.contains(person2));
        assertTrue(result.contains(person3));
    }


    @Test
    public void testRemove() {
        // Add person to testObject
        testObject.persist(person);

        // Call the remove method on testObject with the id of person
        Optional<Person> removedPerson = testObject.remove(person.getId());

        // Assert that a Person was removed
        assertTrue(removedPerson.isPresent());
        assertEquals(person, removedPerson.get());

        // Retrieve all Persons from the testObject
        Collection<Person> allPersons = testObject.find();

        // Assert that the testObject no longer contains the person
        assertFalse(allPersons.contains(person));
    }

    @Test
    public void testRemoveNonExistingItem() {
        // Call the remove method on testObject with a non-existing id
        Optional<Person> removedPerson= testObject.remove(2);
        // Assert that the returned Optional is empty
        assertFalse(removedPerson.isPresent());
    }
}
