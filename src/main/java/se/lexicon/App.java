package se.lexicon;

import se.lexicon.data.impl.AppUserDAOCollection;
import se.lexicon.data.impl.PersonDAOCollection;
import se.lexicon.data.impl.TodoItemDAOCollection;
import se.lexicon.data.impl.TodoItemTaskDAOCollection;
import se.lexicon.model.*;

import javax.swing.plaf.IconUIResource;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Create an instance of AppUserDAOCollection
        AppUserDAOCollection appUserDAO = new AppUserDAOCollection();

        // Create a new AppUser
        AppUser newUser = new AppUser("username", "password", AppRole.ROLE_APP_USER);

        // Use the persist method to save the new user
        appUserDAO.persist(newUser);

        // Use the find method to retrieve a user by username
        Optional<AppUser> foundUser = appUserDAO.find("username");
        foundUser.ifPresent(user -> System.out.println("Found user: " + user));

        // Use the find method without parameters to retrieve all users
        Collection<AppUser> allUsers = appUserDAO.find();
        System.out.println("All users: " + allUsers);

        // Use the remove method to remove a user by username
        Optional<AppUser> removedUser = appUserDAO.remove("username");
        removedUser.ifPresent(user -> System.out.println("Removed user: " + user));

        // Create an instance of PersonDAOCollection
        PersonDAOCollection personDAO = new PersonDAOCollection();

        // Create a new Person
        Person newPerson = new Person();
        newPerson.setEmail("test@test.com");
        newPerson.setFirstName("Daniel");
        newPerson.setLastName("Petersson");

        // Create a new Person
        Person nextPerson = new Person();
        nextPerson.setEmail("test@test.com");
        nextPerson.setFirstName("Alvar");
        nextPerson.setLastName("Petersson");

        // Use the persist method to save the new person
        personDAO.persist(newPerson);
        personDAO.persist(nextPerson);

        // Use the find method to retrieve a person by id
        Optional<Person> foundPerson = personDAO.find(newPerson.getId());
        foundPerson.ifPresent(person -> System.out.println("Found person: " + person));

        // Use the find method without parameters to retrieve all persons
        Collection<Person> allPersons = personDAO.find();
        System.out.println("All persons: " + allPersons);

        // Use the remove method to remove a person by id
        Optional<Person> removedPerson = personDAO.remove(newPerson.getId());
        removedPerson.ifPresent(person -> System.out.println("Removed person: " + person));

        // Create an instance of TodoItemDAOCollection
        TodoItemDAOCollection todoItemDAO = new TodoItemDAOCollection();

        // Create a new TodoItem
        TodoItem newTodoItem = new TodoItem();
        newTodoItem.setTitle("Test Todo");
        newTodoItem.setDone(false);

        // Use the persist method to save the new todo item
        todoItemDAO.persist(newTodoItem);

        // Use the find method to retrieve a todo item by id
        Optional<TodoItem> foundTodoItem = todoItemDAO.find(newTodoItem.getId());
        foundTodoItem.ifPresent(todoItem -> System.out.println("Found todo item: " + todoItem));

        // Use the find method without parameters to retrieve all todo items
        Collection<TodoItem> allTodoItems = todoItemDAO.findAll();
        System.out.println("All todo items: " + allTodoItems);

        // Use the remove method to remove a todo item by id
        Optional<TodoItem> removedTodoItem = todoItemDAO.remove(newTodoItem.getId());
        removedTodoItem.ifPresent(todoItem -> System.out.println("Removed todo item: " + todoItem));

        // Create an instance of TodoItemTaskDAOCollection
        TodoItemTaskDAOCollection todoItemTaskDAO = new TodoItemTaskDAOCollection();

        // Create a new TodoItemTask
        TodoItemTask newTodoItemTask = new TodoItemTask();
        newTodoItemTask.setAssigned(false);

        // Use the persist method to save the new todo item task
        todoItemTaskDAO.persist(newTodoItemTask);

        // Use the find method to retrieve a todo item task by id
        Optional<TodoItemTask> foundTodoItemTask = todoItemTaskDAO.find(newTodoItemTask.getId());
        foundTodoItemTask.ifPresent(todoItemTask -> System.out.println("Found todo item task: " + todoItemTask));

        // Use the find method without parameters to retrieve all todo item tasks
        Collection<TodoItemTask> allTodoItemTasks = todoItemTaskDAO.find();
        System.out.println("All todo item tasks: " + allTodoItemTasks);

        // Use the find method with a boolean parameter to retrieve all assigned/unassigned tasks
        Collection<TodoItemTask> assignedTasks = todoItemTaskDAO.find(true);
        System.out.println("All assigned tasks: " + assignedTasks);

        // Use the findByPersonId method to retrieve tasks assigned to a specific person
        Collection<TodoItemTask> tasksByPerson = todoItemTaskDAO.findByPersonId(1); // replace 1 with the actual person id
        System.out.println("Tasks by person: " + tasksByPerson);

        // Use the remove method to remove a todo item task by id
        Optional<TodoItemTask> removedTodoItemTask = todoItemTaskDAO.remove(newTodoItemTask.getId());
        removedTodoItemTask.ifPresent(todoItemTask -> System.out.println("Removed todo item task: " + todoItemTask));
    }
}
