package se.lexicon.data;

import se.lexicon.model.TodoItemTask;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public interface ITodoItemTaskDAO {
    TodoItemTask persist(TodoItemTask todoItemTask);

    Optional<TodoItemTask> find(int id);

    Collection<TodoItemTask> find();

    Collection<TodoItemTask> find(Predicate<TodoItemTask> filter);

    Optional<TodoItemTask> remove(int id);
}
