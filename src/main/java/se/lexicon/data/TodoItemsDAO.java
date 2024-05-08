package se.lexicon.data;

import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.util.Collection;

public interface TodoItemsDAO {
    TodoItem persist(TodoItem todoItem);

    Collection<TodoItem> findAll();

    Collection<TodoItem> findById(int id);

    Collection<TodoItem> findByDone(boolean status);

    Collection<TodoItem> findByAssignee(int assigneeId);

    Collection<TodoItem> findByAssignee(Person person);

    Collection<TodoItem> findUnassignedTodoItems();

    TodoItem update(TodoItem item);

    TodoItem remove(TodoItem item);
}
