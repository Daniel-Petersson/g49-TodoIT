package se.lexicon.data.impl;

import se.lexicon.data.ITodoItemTaskDAO;
import se.lexicon.model.TodoItemTask;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class TodoItemsDAOCollection implements ITodoItemTaskDAO {
    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        return null;
    }

    @Override
    public Optional<TodoItemTask> find(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<TodoItemTask> find() {
        return List.of();
    }

    @Override
    public Collection<TodoItemTask> find(Predicate<TodoItemTask> filter) {
        return List.of();
    }

    @Override
    public Optional<TodoItemTask> remove(int id) {
        return Optional.empty();
    }
}
