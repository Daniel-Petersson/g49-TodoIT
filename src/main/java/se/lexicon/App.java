package se.lexicon;

import se.lexicon.data.impl.PeopleDAOCollection;
import se.lexicon.data.impl.TodoItemsDAOCollection;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        PeopleDAOCollection peopleDAO = new PeopleDAOCollection();
        TodoItemsDAOCollection todoItemsDAO = new TodoItemsDAOCollection();

        // Test PeopleDAOCollection
        Person person = new Person("John", "Doe");
        person = peopleDAO.create(person);
        System.out.println("Created person: " + person);

        Person foundPerson = peopleDAO.findById(person.getId());
        System.out.println("Found person: " + foundPerson);

        Collection<Person> allPeople = peopleDAO.findAll();
        System.out.println("All people: " + allPeople);

        Collection<Person> peopleByName = peopleDAO.findByName("John", "Doe");
        System.out.println("People by name: " + peopleByName);

        person.setFirstName("Jane");
        person = peopleDAO.update(person);
        System.out.println("Updated person: " + person);

       // boolean removed = peopleDAO.remove(person.getId());
        //System.out.println("Person removed: " + removed);



        // Test TodoItemsDAOCollection

        // Test TodoItemsDAOCollection
        TodoItem todoItem = new TodoItem("Test task", "This is a test task", LocalDate.now(), false, person.getId());
        todoItem = todoItemsDAO.create(todoItem);
        System.out.println("Created todo item: " + todoItem);

        TodoItem unasignedtodoItem = new TodoItem("Test task", "This is a test task", LocalDate.now(), false);
        todoItem = todoItemsDAO.create(unasignedtodoItem);
        System.out.println("Created todo item: " + unasignedtodoItem);

        TodoItem foundTodoItem = todoItemsDAO.findById(todoItem.getId());
        System.out.println("Found todo item: " + foundTodoItem);

        Collection<TodoItem> allTodoItems = todoItemsDAO.findAll();
        System.out.println("All todo items: " + allTodoItems);

        Collection<TodoItem> todoItemsByAssignee = todoItemsDAO.findByAssignee(person);
        System.out.println("Todo items by assignee: " + todoItemsByAssignee);

        Collection<TodoItem> unassignedTodoItems = todoItemsDAO.findUnassignedTodoItems();
        System.out.println("Unassigned todo items: " + unassignedTodoItems);


        boolean removed = todoItemsDAO.remove(todoItem.getId());
        System.out.println("Todo item removed: " + removed);
    }
}
