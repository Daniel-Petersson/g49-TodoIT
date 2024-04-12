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

    // Test the persist method
    @Test
    public void testPersist() {
        // Call the persist method on testObject with appUser
        // Store the returned AppUser in a variable

        // Print out the state of the testObject and the AppUser

        // Retrieve all AppUsers from the testObject

        // Assert that the testObject now contains the appUser
    }

    // Test the find method
    @Test
    public void testFindById() {
        // Add appUser to testObject

        // Call the find method on testObject with the id of appUser

        // Assert that the returned AppUser is the same as the appUser that was added
    }

    @Test
    public void testFindByUsername() {
        // Add AppUsers with different usernames to testObject

        // Call find with username

        // Assert that the returned Collection contains the correct AppUser
    }

    @Test
    public void testFindAll() {
        // Add AppUsers to testObject

        // Call find method without parameters

        // Assert that the returned Collection contains all the AppUsers
    }

    @Test
    public void testRemove() {
        // Add appUser to testObject

        // Call the remove method on testObject with the id of appUser

        // Assert that an AppUser was removed

        // Retrieve all AppUsers from the testObject

        // Assert that the testObject no longer contains the appUser
    }

    @Test
    public void testRemoveNonExistingItem() {
        // Call the remove method on testObject with a non-existing id

        // Assert that the returned Optional is empty
    }
}
