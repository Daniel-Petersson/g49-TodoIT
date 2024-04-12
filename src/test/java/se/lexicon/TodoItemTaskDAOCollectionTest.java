package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoItemTaskDAOCollectionTest {
    // TODO: Declare a TodoItemTaskDAOCollection instance

    @BeforeEach
    public void setup() {
        // TODO: Initialize the TodoItemTaskDAOCollection instance
    }

    // Test the persist method
    @Test
    public void testPersist() {
        // Call the persist method on testObject with todoItemTask
        // Store the returned TodoItemTask in a variable

        // Print out the state of the testObject and the TodoItemTask

        // Retrieve all TodoItemTasks from the testObject

        // Assert that the testObject now contains the todoItemTask
    }

    // Test the find method
    @Test
    public void testFindById() {
        // Add todoItemTask to testObject

        // Call the find method on testObject with the id of todoItemTask

        // Assert that the returned TodoItemTask is the same as the todoItemTask that was added
    }

    @Test
    public void testFindByAssignedStatus() {
        // Add TodoItemTasks with different assigned statuses to testObject

        // Call find with assigned status

        // Assert that the returned Collection contains the correct TodoItemTask
    }

    @Test
    public void testFindAll() {
        // Add TodoItemTasks to testObject

        // Call find method without parameters

        // Assert that the returned Collection contains all the TodoItemTasks
    }

    @Test
    public void testFindByPersonId() {
        // Add TodoItemTasks assigned to different persons to testObject

        // Call findByPersonId with personId

        // Assert that the returned Collection contains the correct TodoItemTask
    }

    @Test
    public void testRemove() {
        // Add todoItemTask to testObject

        // Call the remove method on testObject with the id of todoItemTask

        // Assert that a TodoItemTask was removed

        // Retrieve all TodoItemTasks from the testObject

        // Assert that the testObject no longer contains the todoItemTask
    }

    @Test
    public void testRemoveNonExistingItem() {
        // Call the remove method on testObject with a non-existing id

        // Assert that the returned Optional is empty
    }
}
