package se.lexicon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppUserDAOCollectionTest {
    @BeforeEach
    void setUp() {
        // TODO: Initialize testObject
        // TODO: Initialize some AppUser instances for testing
    }

    @Test
    void testPersist() {
        // TODO: Test if a new AppUser can be persisted successfully
        // TODO: Test if IllegalArgumentException is thrown when trying to persist a null AppUser
        // TODO: Test if EntityAlreadyExistsException is thrown when trying to persist an AppUser that already exists
    }

    @Test
    void testFind_byUsername() {
        // TODO: Test if an AppUser can be found by username
        // TODO: Test if an empty Optional is returned when trying to find an AppUser with a username that does not exist
    }

    @Test
    void testFind_allUsers() {
        // TODO: Test if all AppUser instances can be found
        // TODO: Test if an empty collection is returned when there are no AppUser instances
    }

    @Test
    void testRemove() {
        // TODO: Test if an AppUser can be removed by username
        // TODO: Test if an empty Optional is returned when trying to remove an AppUser with a username that does not exist
    }

    @AfterEach
    void tearDown() {
        // TODO: Clear the users map in the testObject instance to ensure isolation between test cases
    }
}
