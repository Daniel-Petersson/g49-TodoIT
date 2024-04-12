package se.lexicon.data;

import se.lexicon.model.TodoItem;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ITodoItemDAO {
    TodoItem persist(TodoItem todoItem);

    Optional<TodoItem> find(int id);

    Collection<TodoItem> find(boolean done);

    Collection<TodoItem> find(String title);

    Collection<TodoItem> findByPersonId(int personId);

    Collection<TodoItem> find(LocalDate date, boolean isBefore);

    List<TodoItem> findAll();

    Optional<TodoItem> remove(int id);

}
