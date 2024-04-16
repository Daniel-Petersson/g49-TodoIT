package se.lexicon.data.impl;

import se.lexicon.data.ITodoItemTaskDAO;
import se.lexicon.data.sequencers.IdSequencer;
import se.lexicon.data.sequencers.decaprecated.TodoItemTaskIdSequencer;
import se.lexicon.data.util.EntityType;
import se.lexicon.exception.EntityAlreadyExistsException;
import se.lexicon.model.TodoItemTask;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class implements the ITodoItemTaskDAO interface and provides concrete implementations for each of the operations that can be performed on TodoItemTask objects.
 */
public class TodoItemTaskDAOCollection implements ITodoItemTaskDAO {

    /**
     * A list of TodoItemTask objects. This list is used to store all the TodoItemTask instances managed by this DAO.
     */
    private final Map<Integer, TodoItemTask> itemTasks = new HashMap<>();

    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        if (todoItemTask == null) throw new IllegalArgumentException("Todo Item Task cannot be null");
        Optional<TodoItemTask> taskOptional = find(todoItemTask.getId());
        if (taskOptional.isPresent()) throw new EntityAlreadyExistsException("Task already exist");
        IdSequencer sequencer = IdSequencer.getInstance();
        int nextTodoItemTaskId = sequencer.nextId(EntityType.TODO_ITEM_TASK);
        todoItemTask.setId(nextTodoItemTaskId);
        itemTasks.put(nextTodoItemTaskId, todoItemTask);
        return todoItemTask;
    }

    @Override
    public Optional<TodoItemTask> find(int id) {
        return Optional.ofNullable(itemTasks.get(id));
    }

    @Override
    public Collection<TodoItemTask> find() {
        return itemTasks.values();
    }

    @Override
    public Collection<TodoItemTask> find(boolean status) {
        return itemTasks.values().stream()
                .filter(item -> item.isAssigned() == status)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItemTask> findByPersonId(int id) {
        return itemTasks.values().stream()
                .filter(item -> item.getAssignee() != null && item.getAssignee().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TodoItemTask> remove(int id) {
        return Optional.ofNullable(itemTasks.remove(id));
    }
}