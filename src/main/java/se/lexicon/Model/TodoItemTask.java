package se.lexicon.Model;

import java.time.LocalDate;

public class TodoItemTask {
    //Fields
    private int id;
    private String title;
    private String taskDescription;
    private LocalDate deadLine;
    private boolean done;
    private Person creator;

    //Constructor

    //Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTaskDescription(String description) {
        this.taskDescription = description;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = LocalDate.parse(deadLine);
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    //Getters

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public Person getCreator() {
        return creator;
    }

    //Methods
    public boolean isOverdue() {
        if (this.deadLine.isAfter(LocalDate.now())) {
            return true;
        }
        return false;
    }

    public void getSummary() {
        System.out.println("ID: " + id + ", Title: " + title + ", Description: " + taskDescription + ", Deadline: " + deadLine + ", Done: " + done);
        if (creator != null) {
            System.out.print(" Creator Name: " + creator.getName());
        } else {
            System.out.print(" No owner assigned.");
        }
        System.out.println();
    }
}
