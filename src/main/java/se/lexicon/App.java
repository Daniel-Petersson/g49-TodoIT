package se.lexicon;

import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;
import se.lexicon.Model.TodoItemTask;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {   //Test Person
        Person person1 = new Person();
        person1.setFirstName("Daniel");
        person1.setLastName("Petersson");
        person1.setEmail("email@email.com");
        person1.getSummary();

        Person person2 = new Person();
        person2.setFirstName("Kalle");
        person2.setLastName("Klasson");

        //Test TodoItem
        TodoItem item1 = new TodoItem();
        item1.setTitle("Clean garage");
        item1.setTaskDescription("Sort all the junk");
        item1.setDeadLine("2024-04-01");
        item1.setDone(false);
        item1.setCreator(person1);
        item1.getSummary();

        //Test TodoItemTask
        TodoItemTask task = new TodoItemTask();
        task.setAssigned(false);
        task.setAssignee(person2);
        task.setTodoItem(item1);
        task.getSummary();
    }
}
