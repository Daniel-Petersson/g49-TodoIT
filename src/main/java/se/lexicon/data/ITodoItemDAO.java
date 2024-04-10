package se.lexicon.data;

import se.lexicon.model.TodoItem;


import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface ITodoItemDAO {
    TodoItem persist(TodoItem todoItem);
    Optional<TodoItem> findById(int id);
    Collection<TodoItem> findAllByDoneStatus(boolean done);
    Collection<TodoItem> findByTitleContains(String title);
    Collection<TodoItem> findByPersonId(int personId);
    Collection<TodoItem> findByDeadlineBefore(LocalDate date);
    Collection<TodoItem> findByDeadlineAfter(LocalDate date);
    void remove(int id);

}
