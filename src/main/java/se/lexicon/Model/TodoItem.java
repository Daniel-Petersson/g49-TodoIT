package se.lexicon.Model;

public class TodoItem {
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
        this.todoItem = todoItem;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    //Getters

    public int getId() {
        return id;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public Person getAssignee() {
        return assignee;
    }

    //Methods

    public void getSummary() {
        System.out.println("ID: " + id + ", Assigned: " + assigned + ", TodoItem: " + todoItem);
        if (assignee != null) {
            System.out.print(" Assigned Name: " + assignee.getName());
        } else {
            System.out.print(" No owner assigned.");
        }
        System.out.println();
    }
}
