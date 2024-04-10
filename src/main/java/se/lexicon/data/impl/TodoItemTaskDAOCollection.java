package se.lexicon.data.impl;

import se.lexicon.data.ITodoItemTaskDAO;
import se.lexicon.data.sequencers.TodoItemTaskIdSequencer;
import se.lexicon.model.TodoItemTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TodoItemTaskDAOCollection implements ITodoItemTaskDAO {

    // List to store TodoItemTask objects
    List<TodoItemTask> itemTasks = new ArrayList<>();

    // Method to persist a TodoItemTask object
    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        // Check if the input TodoItemTask is null
        if (todoItemTask == null) throw new IllegalArgumentException("Todo Item Task cannot be null");

        // Check if a TodoItemTask with the same id already exists in the list
        Optional<TodoItemTask> taskOptional = findById(todoItemTask.getId());
        if (taskOptional.isPresent()) throw new IllegalArgumentException("Task already exist");

        // Generate a new id for the TodoItemTask
        int id = TodoItemTaskIdSequencer.nextId();

        // Add the TodoItemTask to the list
        itemTasks.add(todoItemTask);

        // Return the persisted TodoItemTask
        return todoItemTask;
    }

    // Method to find a TodoItemTask by id
    @Override
    public Optional<TodoItemTask> findById(int id) {
        for (TodoItemTask existingId : itemTasks) {
            if (existingId.getId() == id) {
                return Optional.of(existingId);
            }
        }
        return Optional.empty();
    }

    // Method to find all TodoItemTask objects
    @Override
    public Collection<TodoItemTask> findAll() {
        return new ArrayList<>(itemTasks);
    }

    // Method to find TodoItemTask objects by assigned status
    @Override
    public Collection<TodoItemTask> findByAssignedStatus(boolean status) {
        List<TodoItemTask> assignedItems = new ArrayList<>();
        for (TodoItemTask item : itemTasks) {
            if (item.isAssigned() == status) {
                assignedItems.add(item);
            }
        }
        return assignedItems;
    }

    // Method to find TodoItemTask objects by person id
    @Override
    public Collection<TodoItemTask> findByPersonId(int id) {
        List<TodoItemTask> itemByAssigneeId = new ArrayList<>();
        for (TodoItemTask assignedId : itemTasks) {
            if (assignedId.getAssignee().getId() == id) {
                itemByAssigneeId.add(assignedId);
            }
        }
        return itemByAssigneeId;
    }

    // Method to remove a TodoItemTask by id
    @Override
    public void remove(int id) {
        // Find the TodoItemTask by id
        Optional<TodoItemTask> taskOptional = findById(id);

        // If the TodoItemTask is not found, throw an exception
        if (!taskOptional.isPresent()) {
            throw new IllegalArgumentException("Task not found");
        }

        // If the TodoItemTask is found, remove it from the list
        itemTasks.remove(taskOptional.get());
    }
}