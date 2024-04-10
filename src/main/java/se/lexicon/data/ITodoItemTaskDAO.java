package se.lexicon.data;

import se.lexicon.model.TodoItemTask;

import java.util.Collection;
import java.util.Optional;

public interface ITodoItemTaskDAO {
    TodoItemTask persist(TodoItemTask todoItemTask);
    Optional<TodoItemTask> find(int id);
    Collection<TodoItemTask> find();
    Collection<TodoItemTask> find(boolean status);
    Collection<TodoItemTask> findByPersonId(int id);
    void remove(int id);
}
