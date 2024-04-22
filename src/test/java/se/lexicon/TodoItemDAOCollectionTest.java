package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.data.impl.TodoItemDAOCollection;
import se.lexicon.model.TodoItem;
import se.lexicon.exception.EntityAlreadyExistsException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemDAOCollectionTest {
    private TodoItemDAOCollection testObject;
    private TodoItem testTodoItem;

    @BeforeEach
    public void setup() {
        testObject = new TodoItemDAOCollection();
        testTodoItem = new TodoItem();
        testTodoItem.setTitle("Test");

    }

    @Test
    public void persist_ShouldPersistTodoItem_WhenTodoItemDoesNotExist() {
        TodoItem persistedTodoItem = testObject.persist(testTodoItem);
        assertEquals(testTodoItem, persistedTodoItem);
        assertTrue(testObject.find(testTodoItem.getId()).isPresent());
    }

    @Test
    public void persist_ShouldThrowException_WhenTodoItemAlreadyExists() {
        testObject.persist(testTodoItem);
        assertThrows(EntityAlreadyExistsException.class, () -> testObject.persist(testTodoItem));
    }

    @Test
    public void persist_ShouldThrowException_WhenTodoItemIsNull() {
        assertThrows(IllegalArgumentException.class, () -> testObject.persist(null));
    }

    @Test
    public void find_ShouldReturnTodoItem_WhenTodoItemExists() {
        testObject.persist(testTodoItem);
        Optional<TodoItem> foundTodoItem = testObject.find(testTodoItem.getId());
        assertTrue(foundTodoItem.isPresent());
        assertEquals(testTodoItem, foundTodoItem.get());
    }

    @Test
    public void find_ShouldReturnEmptyOptional_WhenTodoItemDoesNotExist() {
        Optional<TodoItem> foundTodoItem = testObject.find(999);
        assertFalse(foundTodoItem.isPresent());
    }

    @Test
    public void remove_ShouldRemoveTodoItem_WhenTodoItemExists() {
        testObject.persist(testTodoItem);
        Optional<TodoItem> removedTodoItem = testObject.remove(testTodoItem.getId());
        assertTrue(removedTodoItem.isPresent());
        assertEquals(testTodoItem, removedTodoItem.get());
        assertFalse(testObject.find(testTodoItem.getId()).isPresent());
    }

    @Test
    public void remove_ShouldReturnEmptyOptional_WhenTodoItemDoesNotExist() {
        Optional<TodoItem> removedTodoItem = testObject.remove(999);
        assertFalse(removedTodoItem.isPresent());
    }
}