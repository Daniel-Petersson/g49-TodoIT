package se.lexicon.model;

import java.util.Objects;

public class TodoItemTask {
    //Fields
    private int id;
    private boolean assigned;
    private TodoItem todoItem;
    private Person assignee;
    //Constructor

    //Setters

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;

    }

    public void setTodoItem(TodoItem todoItem) {
        if (todoItem == null) {
            throw new IllegalArgumentException("Todo item description cannot be null or empty.");
        }
        this.todoItem = todoItem;
    }

    public void setAssignee(Person assignee) {
        if (assignee == null) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.assignee = assignee;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Getters
    


    public int getId() {
        return id;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public Person getAssignee() {
        return assignee;
    }

    //Methods
    @Override
    public String toString() {
        return "ToDoItemTask { " + "id: " + id + ", Assigned: " + assigned + ", TodoItem: " + todoItem + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, todoItem, assigned);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TodoItemTask task = (TodoItemTask) obj;
        return id == task.id && assigned == task.assigned && Objects.equals(todoItem, task.todoItem);
    }
}
