package se.lexicon.data;

import se.lexicon.model.TodoItem;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ITodoItemDAO {
    TodoItem persist(TodoItem todoItem);

    Optional<TodoItem> find(int id);

    Collection<TodoItem> find(Predicate<TodoItem> filter);

    List<TodoItem> findAll();

    Optional<TodoItem> remove(int id);

}
