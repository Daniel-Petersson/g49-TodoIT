package se.lexicon;

public class PersonDAOCollectionTest {
    package se.lexicon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.data.impl.PersonDAOCollection;
import se.lexicon.model.Person;

    public class PersonDAOCollectionTest {

        private PersonDAOCollection testObject;

        @BeforeEach
        void setUp() {
            // TODO: Initialize testObject
            // TODO: Initialize some Person instances for testing
        }

        @Test
        void testPersist() {
            // TODO: Test if a new Person can be persisted successfully
            // TODO: Test if IllegalArgumentException is thrown when trying to persist a null Person
            // TODO: Test if EntityAlreadyExistsException is thrown when trying to persist a Person that already exists
        }

        @Test
        void testFind_byId() {
            // TODO: Test if a Person can be found by id
            // TODO: Test if an empty Optional is returned when trying to find a Person with an id that does not exist
        }

        @Test
        void testFind_byEmail() {
            // TODO: Test if a Person can be found by email
            // TODO: Test if an empty Optional is returned when trying to find a Person with an email that does not exist
            // TODO: Test if IllegalArgumentException is thrown when trying to find a Person with an invalid email format
        }

        @Test
        void testFind_allPersons() {
            // TODO: Test if all Person instances can be found
            // TODO: Test if an empty collection is returned when there are no Person instances
        }

        @Test
        void testRemove() {
            // TODO: Test if a Person can be removed by id
            // TODO: Test if an empty Optional is returned when trying to remove a Person with an id that does not exist
        }

        @AfterEach
        void tearDown() {
            // TODO: Clear the persons map in the testObject instance to ensure isolation between test cases
        }
    }
}
