package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoItemTaskDAOCollectionTest {
    // TODO: Declare a TodoItemTaskDAOCollection instance

    @BeforeEach
    public void setup() {
        // TODO: Initialize the TodoItemTaskDAOCollection instance
    }

    @Test
    public void testPersist() {
        // TODO: Create a new TodoItemTask

        // TODO: Call the persist method on TodoItemTaskDAOCollection with the new TodoItemTask

        // TODO: Retrieve the persisted TodoItemTask from TodoItemTaskDAOCollection

        // TODO: Assert that the retrieved TodoItemTask is the same as the one we added
    }

    @Test
    public void testFindById() {
        // TODO: Add a TodoItemTask to TodoItemTaskDAOCollection

        // TODO: Call the find method on TodoItemTaskDAOCollection with the id of the added TodoItemTask

        // TODO: Assert that the returned TodoItemTask is the same as the one we added
    }

    @Test
    public void testFindAll() {
        // TODO: Add multiple TodoItemTasks to TodoItemTaskDAOCollection

        // TODO: Call the find method on TodoItemTaskDAOCollection

        // TODO: Assert that the returned Collection contains all the TodoItemTasks we added
    }

    @Test
    public void testFindByAssignedStatus() {
        // TODO: Add TodoItemTasks with different assigned statuses to TodoItemTaskDAOCollection

        // TODO: Call the find method on TodoItemTaskDAOCollection with a specific assigned status

        // TODO: Assert that the returned Collection contains all the TodoItemTasks with the specified assigned status
    }

    @Test
    public void testFindByPersonId() {
        // TODO: Add TodoItemTasks assigned to different persons to TodoItemTaskDAOCollection

        // TODO: Call the findByPersonId method on TodoItemTaskDAOCollection with a specific person id

        // TODO: Assert that the returned Collection contains all the TodoItemTasks assigned to the person with the specified id
    }

    @Test
    public void testRemove() {
        // TODO: Add a TodoItemTask to TodoItemTaskDAOCollection

        // TODO: Call the remove method on TodoItemTaskDAOCollection with the id of the added TodoItemTask

        // TODO: Assert that the returned TodoItemTask is the same as the one we removed

        // TODO: Assert that the TodoItemTask is no longer in the TodoItemTaskDAOCollection
    }
}
