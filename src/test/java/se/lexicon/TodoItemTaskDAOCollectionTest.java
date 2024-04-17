import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.data.impl.TodoItemTaskDAOCollection;
import se.lexicon.model.TodoItemTask;
import se.lexicon.exception.EntityAlreadyExistsException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTaskDAOCollectionTest {
    private TodoItemTaskDAOCollection testObject;
    private TodoItemTask testTodoItemTask;

    @BeforeEach
    public void setup() {
        testObject = new TodoItemTaskDAOCollection();
        testTodoItemTask = new TodoItemTask();
    }

    @Test
    public void persist_ShouldPersistTodoItemTask_WhenTodoItemTaskDoesNotExist() {
        TodoItemTask persistedTodoItemTask = testObject.persist(testTodoItemTask);
        assertEquals(testTodoItemTask, persistedTodoItemTask);
        assertTrue(testObject.find(testTodoItemTask.getId()).isPresent());
    }

    @Test
    public void persist_ShouldThrowException_WhenTodoItemTaskAlreadyExists() {
        testObject.persist(testTodoItemTask);
        assertThrows(EntityAlreadyExistsException.class, () -> testObject.persist(testTodoItemTask));
    }

    @Test
    public void persist_ShouldThrowException_WhenTodoItemTaskIsNull() {
        assertThrows(IllegalArgumentException.class, () -> testObject.persist(null));
    }

    @Test
    public void find_ShouldReturnTodoItemTask_WhenTodoItemTaskExists() {
        testObject.persist(testTodoItemTask);
        Optional<TodoItemTask> foundTodoItemTask = testObject.find(testTodoItemTask.getId());
        assertTrue(foundTodoItemTask.isPresent());
        assertEquals(testTodoItemTask, foundTodoItemTask.get());
    }

    @Test
    public void find_ShouldReturnEmptyOptional_WhenTodoItemTaskDoesNotExist() {
        Optional<TodoItemTask> foundTodoItemTask = testObject.find(999);
        assertFalse(foundTodoItemTask.isPresent());
    }

    @Test
    public void remove_ShouldRemoveTodoItemTask_WhenTodoItemTaskExists() {
        testObject.persist(testTodoItemTask);
        Optional<TodoItemTask> removedTodoItemTask = testObject.remove(testTodoItemTask.getId());
        assertTrue(removedTodoItemTask.isPresent());
        assertEquals(testTodoItemTask, removedTodoItemTask.get());
        assertFalse(testObject.find(testTodoItemTask.getId()).isPresent());
    }

    @Test
    public void remove_ShouldReturnEmptyOptional_WhenTodoItemTaskDoesNotExist() {
        Optional<TodoItemTask> removedTodoItemTask = testObject.remove(999);
        assertFalse(removedTodoItemTask.isPresent());
    }
}