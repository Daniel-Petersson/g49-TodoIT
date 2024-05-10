package se.lexicon.model;

import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {
    //Fields
    private int id;
    private String title;
    private String taskDescription;
    private LocalDate deadLine;
    private boolean done;
    private Person assignee;
    private int assigneeId;

    //Constructor
    //executeQuery
    public TodoItem(int id, String title, String taskDescription, LocalDate deadLine, boolean done, int assigneeId) {
        this.id = id;
        this.title = title;
        this.taskDescription = taskDescription;
        this.deadLine = deadLine;
        this.done = done;
        this.assigneeId = assigneeId;
    }

    public TodoItem(String title, String taskDescription, LocalDate deadLine, boolean done) {
        this.title = title;
        this.taskDescription = taskDescription;
        this.deadLine = deadLine;
        this.done = done;
    }

    //UpdateQuery

    public TodoItem(String title, String taskDescription, LocalDate deadLine, boolean done, int assigneeId) {
        this.title = title;
        this.taskDescription = taskDescription;
        this.deadLine = deadLine;
        this.done = done;
        this.assigneeId = assigneeId;
    }

    //Setters

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public void setTaskDescription(String description) {
        this.taskDescription = description;
    }

    public void setDeadLine(String deadLine) {
        if (deadLine == null) {
            throw new IllegalArgumentException("Deadline cannot be null or empty");
        }
        this.deadLine = LocalDate.parse(deadLine);
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
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

    public Person getAssignee() {
        return assignee;
    }

    public int getAssigneeId() {
        return this.assigneeId;
    }

    //Methods
    public boolean isOverdue() {
        if (this.deadLine.isAfter(LocalDate.now())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ToDoItem { " + "id: " + id + ", Title: " + title + ", Description: " + taskDescription + ", Deadline: " + deadLine + ", Done: " + done + ", Creator: " + assignee + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, taskDescription, deadLine, done, assignee);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TodoItem task = (TodoItem) obj;
        return id == task.id && done == task.done && Objects.equals(title, task.title) && Objects.equals(taskDescription, task.taskDescription) && Objects.equals(deadLine, task.deadLine) && Objects.equals(assignee, task.assignee);
    }
}
