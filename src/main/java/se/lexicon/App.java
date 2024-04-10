package se.lexicon;

import se.lexicon.model.*;

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
        //person1.getSummary(); Replaced with toString()
        System.out.println(person1.toString());
        System.out.println(person1.hashCode());


        Person person2 = new Person();
        person2.setFirstName("Kalle");
        person2.setLastName("Klasson");
        System.out.println(person1.equals(person2));//false
        //Test TodoItem
        TodoItem item1 = new TodoItem();
        TodoItem item2 = new TodoItem();
        item1.setTitle("Clean garage");
        item1.setTaskDescription("Sort all the junk");
        item1.setDeadLine("2024-04-01");
        item1.setDone(false);
        item1.setCreator(person1);
        //item1.getSummary();// Replaced with toString()
        System.out.println(item1.toString());
        System.out.println(item1.hashCode());
        System.out.println(item1.equals(item2));//False

        //Test TodoItemTask
        TodoItemTask task = new TodoItemTask();
        TodoItemTask task1 = new TodoItemTask();
        task.setAssigned(false);
        task.setAssignee(person2);
        task.setTodoItem(item1);
        //task.getSummary();// Replaced with toString()
        System.out.println(task.toString());
        System.out.println(task.hashCode());
        System.out.println(task1.hashCode());
        System.out.println(task.equals(task1));//False



        //Test AppUser
        AppUser appUser = new AppUser("Leiden", "test123", AppRole.ROLE_APP_USER);
        AppUser appUser1 = new AppUser("Leiden", "test123", AppRole.ROLE_APP_USER);
        System.out.println(appUser.toString());
        System.out.println(appUser.hashCode());
        System.out.println(appUser.equals(appUser1));//true


    }
}
