package se.lexicon.data;

import se.lexicon.model.TodoItemTask;

import java.util.Collection;
import java.util.Optional;

public interface ITodoItemTaskDAO {
    TodoItemTask persist(TodoItemTask todoItemTask);
    Optional<TodoItemTask> findById(int id);
    Collection<TodoItemTask> findAll();
    Collection<TodoItemTask> findByAssignedStatus(boolean status);
    Collection<TodoItemTask> findByPersonId(int id);
    void remove(int id);
}
