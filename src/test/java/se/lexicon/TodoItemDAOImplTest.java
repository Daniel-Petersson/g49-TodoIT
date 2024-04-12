package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.data.impl.TodoItemDAOCollection;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemDAOImplTest {
    private TodoItemDAOCollection todoDAO;
    private TodoItem doneTodoItem;
    private TodoItem undoneTodoItem;

    // This method will run before each test, it's a good place to initialize the objects you're going to test
    @BeforeEach
    public void setup() {
        todoDAO = new TodoItemDAOCollection();
        doneTodoItem = new TodoItem();
        // Set properties for undoneTodoItem
        doneTodoItem.setTitle("Test Todo");
        doneTodoItem.setTaskDescription("This is a test task");
        doneTodoItem.setDone(true);
        doneTodoItem.setTitle("Test title");


        undoneTodoItem = new TodoItem();
        // Set properties for undoneTodoItem
        undoneTodoItem.setTitle("Test Next Todo");
        undoneTodoItem.setTaskDescription("This is also test task");
        undoneTodoItem.setDone(false);

    }

    // Test the persist method
    @Test
    public void testPersist() {
        // Call the persist method on todoDAO with doneTodoItem
        TodoItem actualValue = todoDAO.persist(doneTodoItem);
        TodoItem expectedValue = doneTodoItem;

        // Print out the state of the todoDAO and the TodoItem
        System.out.println("todoDAO: " + todoDAO);
        System.out.println("doneTodoItem: " + doneTodoItem);

        // Retrieve all TodoItems from the todoDAO
        List<TodoItem> allTodoItems = todoDAO.findAll();

        // Assert that the todoDAO now contains doneTodoItem
        assertTrue(allTodoItems.contains(doneTodoItem));
    }

    // Test the find method
    @Test
    public void testFindById() {
        // Add doneTodoItem to todoDAO
        todoDAO.persist(doneTodoItem);
        // Call the find method on todoDAO with the id of doneTodoItem
        Optional<TodoItem> foundItem = todoDAO.find(doneTodoItem.getId());
        // Assert that the returned TodoItem is the same as doneTodoItem
        assertTrue(foundItem.isPresent());
        assertEquals(doneTodoItem, foundItem.get());
    }

    @Test
    public void testFindByStatus() {
        // Add TodoItems with different done statuses to todoDAO

        todoDAO.persist(doneTodoItem);

        todoDAO.persist(undoneTodoItem);
        // Call find with done status
        Collection<TodoItem> foundItems = todoDAO.find(doneTodoItem.isDone());
        // Assert that the returned Collection contains the correct TodoItem
        assertTrue(foundItems.contains(doneTodoItem));
        assertFalse(foundItems.contains(undoneTodoItem));

    }

    @Test
    public void testFindByTitle() {
        // Add TodoItems with different titles to todoDAO

        todoDAO.persist(doneTodoItem);
        todoDAO.persist(undoneTodoItem);
        // Call find with title
        Collection<TodoItem> foundItems = todoDAO.find("Test title");
        // Assert that the returned Collection contains the correct TodoItem
        assertTrue(foundItems.contains(doneTodoItem));
        assertFalse(foundItems.contains(undoneTodoItem));
    }

    @Test
    public void testFindByPersonId() {
        // Add TodoItems with different titles to todoDAO
        TodoItem itemWithPersonId = new TodoItem();
        Person person1 = new Person();
        person1.setId(1);
        itemWithPersonId.setCreator(person1);
        todoDAO.persist(itemWithPersonId);

        TodoItem itemWithOutPersonId = new TodoItem();
        Person person2 = new Person();
        person2.setId(2);
        itemWithOutPersonId.setCreator(person2);
        todoDAO.persist(itemWithOutPersonId);
        // Call findByPersonId with personId
        Collection<TodoItem> foundItems = todoDAO.findByPersonId(1);
        // Assert that the returned Collection contains the correct TodoItem
        assertTrue(foundItems.contains(itemWithPersonId));
        assertFalse(foundItems.contains(itemWithOutPersonId));
    }


    // Test the remove method
    @Test
    public void testRemove() {
        // Add doneTodoItem to todoDAO
        todoDAO.persist(doneTodoItem);
        todoDAO.persist(undoneTodoItem);
        // Call the remove method on todoDAO with the id of doneTodoItem
        todoDAO.remove(doneTodoItem.getId());
        // Retrieve all TodoItems from the todoDAO
        List<TodoItem> allTodoItems = todoDAO.findAll();

        // Assert that the todoDAO no longer contains doneTodoItem
        assertFalse(allTodoItems.contains(doneTodoItem));
    }

    @Test
    public void testRemoveNonExistingItem() {
        // Assert that the todoDAO throws IllegalExpression when calling remoce method on a non existing item
        assertThrows(IllegalArgumentException.class, () -> todoDAO.remove(3));
    }
}
