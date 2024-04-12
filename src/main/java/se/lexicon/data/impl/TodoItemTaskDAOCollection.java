package se.lexicon.data.impl;

import se.lexicon.data.ITodoItemTaskDAO;
import se.lexicon.data.sequencers.TodoItemTaskIdSequencer;
import se.lexicon.exception.EntityAlreadyExistsException;
import se.lexicon.model.TodoItemTask;

import java.util.*;

/**
 * This class implements the ITodoItemTaskDAO interface and provides concrete implementations for each of the operations that can be performed on TodoItemTask objects.
 */
public class TodoItemTaskDAOCollection implements ITodoItemTaskDAO {

    /**
     * A list of TodoItemTask objects. This list is used to store all the TodoItemTask instances managed by this DAO.
     */
    private final Map<Integer,TodoItemTask> itemTasks = new HashMap<>();

    /**
     * Method to persist a TodoItemTask object.
     * @param todoItemTask The TodoItemTask object to be persisted.
     * @return The persisted TodoItemTask object.
     * @throws IllegalArgumentException If the input TodoItemTask is null or already exists in the list.
     */
    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        if (todoItemTask == null) throw new IllegalArgumentException("Todo Item Task cannot be null");
        Optional<TodoItemTask> taskOptional = find(todoItemTask.getId());
        if (taskOptional.isPresent()) throw new EntityAlreadyExistsException("Task already exist");
        int id = TodoItemTaskIdSequencer.nextId();
        todoItemTask.setId(id);
        itemTasks.put(id,todoItemTask);
        return todoItemTask;
    }

    /**
     * Method to find a TodoItemTask by id.
     * @param id The id of the TodoItemTask to be found.
     * @return An Optional containing the found TodoItemTask, or an empty Optional if no TodoItemTask was found.
     */
    @Override
    public Optional<TodoItemTask> find(int id) {
        return Optional.ofNullable(itemTasks.get(id));
    }

    /**
     * Method to find all TodoItemTask objects.
     * @return A Collection containing all TodoItemTask objects.
     */
    @Override
    public Collection<TodoItemTask> find() {
        return new ArrayList<>(itemTasks.values());
    }

    /**
     * Method to find TodoItemTask objects by assigned status.
     * @param status The assigned status to search for.
     * @return A Collection containing all TodoItemTask objects with the given assigned status.
     */
    @Override
    public Collection<TodoItemTask> find(boolean status) {
        List<TodoItemTask> assignedItems = new ArrayList<>();
        for (TodoItemTask item : itemTasks.values()) {
            if (item.isAssigned() == status) {
                assignedItems.add(item);
            }
        }
        return assignedItems;
    }

    /**
     * Method to find TodoItemTask objects by person id.
     * @param id The id of the person to search for.
     * @return A Collection containing all TodoItemTask objects assigned to the person with the given id.
     */
    @Override
    public Collection<TodoItemTask> findByPersonId(int id) {
        List<TodoItemTask> itemByAssigneeId = new ArrayList<>();
        for (TodoItemTask assignedId : itemTasks.values()) {
            if (assignedId.getAssignee().getId() == id) {
                itemByAssigneeId.add(assignedId);
            }
        }
        return itemByAssigneeId;
    }

    /**
     * Method to remove a TodoItemTask by id.
     * @param id The id of the TodoItemTask to be removed.
     * @throws IllegalArgumentException If the TodoItemTask is not found.
     */
    @Override
    public Optional<TodoItemTask> remove(int id) {
       return Optional.ofNullable(itemTasks.remove(id));
    }
}