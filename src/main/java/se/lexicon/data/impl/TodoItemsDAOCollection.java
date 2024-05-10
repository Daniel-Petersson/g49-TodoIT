package se.lexicon.data.impl;

import se.lexicon.data.IPeopleDAO;
import se.lexicon.data.ITodoItemsDAO;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;



public class TodoItemsDAOCollection implements ITodoItemsDAO {

    @Override
    public TodoItem create(TodoItem todoItem) {
        String insertQuery = "INSERT INTO todo_item(title,description,deadline,done,assignee_id)VALUES(?,?,?,?,?)";
        try (Connection connection = se.lexicon.data.util.SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery,PreparedStatement.RETURN_GENERATED_KEYS)) {
            setTodoItem(todoItem,statement);
            IPeopleDAO peopleDAO = new PeopleDAOCollection();
            Person assignee = peopleDAO.findById(todoItem.getAssigneeId());
            if (assignee == null && todoItem.getAssigneeId() != 0) {
                System.out.println("Error: Assignee with id " + todoItem.getAssigneeId() + " does not exist");
                return null;
            }
            int numberOfInsertedRows = statement.executeUpdate();
            if (numberOfInsertedRows>0){
                try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        int generatedTodoItemId = generatedKeys.getInt(1);
                        todoItem.setId(generatedTodoItemId);
                        System.out.println("Todo item created with the id: " + generatedTodoItemId);
                    }
                }
            }else {
                System.out.println("Failed to create todo item");
            }
        }catch (SQLException e){
            System.out.println("Error inserting new todo item");
            e.printStackTrace();
        }
        return todoItem;
    }

    @Override
    public Collection<TodoItem> findAll() {
        String findAllQuery = "SELECT * FROM todo_item";
        Collection<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = se.lexicon.data.util.SQLConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(findAllQuery);
        ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                todoItems.add(getTodoItems(resultSet));
            }
        }catch (SQLException e){
            System.out.println("Error getting connection");
        }
        todoItems.forEach(System.out::println);
        return todoItems;
    }

    @Override
    public TodoItem findById(int id) {
        String findByIdQuery = "SELECT * FROM todo_item WHERE todo_id = ?";
        TodoItem todoItem = null;
        try (Connection connection = se.lexicon.data.util.SQLConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(findByIdQuery))  {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                todoItem = getTodoItems(resultSet);
            }else System.out.println("No match found");

        }catch (SQLException e){
            System.out.println("Error getting connection");
        }
        return todoItem;
    }

    @Override
    public Collection<TodoItem> findByDone(boolean status) {
        String findByDoneQuery = "SELECT * FROM todo_item WHERE done = ?";
        Collection<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = se.lexicon.data.util.SQLConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(findByDoneQuery)){
            statement.setBoolean(1,status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) todoItems.add(getTodoItems(resultSet));
        }catch (SQLException e){
            System.out.println("Error getting connection");
        }
        return todoItems;
    }

    @Override
    public Collection<TodoItem> findByAssignee(int assigneeId) {
        String findByAssigneeIdQuery = "SELECT * FROM todo_item WHERE assignee_id = ?";
        Collection<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = se.lexicon.data.util.SQLConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(findByAssigneeIdQuery)){
            statement.setInt(1,assigneeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) todoItems.add(getTodoItems(resultSet));
        }catch (SQLException e) {
            System.out.println("Error getting connection");
        }
        return todoItems;
    }

    @Override
    public Collection<TodoItem> findByAssignee(Person person) {
        String findByAssigneeQuery = "SELECT * FROM todo_item WHERE assignee_id = ?";
        Collection<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = se.lexicon.data.util.SQLConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(findByAssigneeQuery)){
            statement.setInt(1,person.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) todoItems.add(getTodoItems(resultSet));

        }catch (SQLException e){
            System.out.println("Error getting connection");
        }
        return todoItems;
    }

    @Override
    public Collection<TodoItem> findUnassignedTodoItems() {
        String findByUnassignedQuery = "SELECT * FROM todo_item WHERE assignee_id is null";
        Collection<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = se.lexicon.data.util.SQLConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(findByUnassignedQuery)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) todoItems.add(getTodoItems(resultSet));
        }catch (SQLException e){
            System.out.println("Error getting connection");
        }
        return todoItems;
    }

    @Override
    public TodoItem update(TodoItem todoItem) {
        String updateQuery = "UPDATE todo_item SET title = ?, description = ?, deadline = ?, done = ?, assignee_id =? WHERE todo_id = ?";
        try (Connection connection = se.lexicon.data.util.SQLConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(updateQuery)){
            setTodoItem(todoItem, statement);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated >0) {
                System.out.println("Update successful");
            }else {
                System.out.println("No todo item found with the given ID");
            }
        }catch (SQLException e){
            System.out.println("Error getting connection");
        }
        return todoItem;
    }

    @Override
    public boolean remove(int id) {
        String removeQuery = "DELETE FROM todo_item WHERE todo_id = ?";
        try (Connection connection = se.lexicon.data.util.SQLConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(removeQuery)){
            statement.setInt(1,id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted>0) {
                System.out.println("Todo item deleted successfully: " +id);
                return true;
            }else {
                System.out.println("Todo item with " + id + " not found");
            }
        }catch (SQLException e) {
            System.out.println("Error executing remove query");
        }
        return false;
    }

    private static void setTodoItem(TodoItem todoItem, PreparedStatement statement) throws SQLException {
        statement.setString(1, todoItem.getTitle());
        statement.setString(2, todoItem.getTaskDescription());
        statement.setDate(3,Date.valueOf(todoItem.getDeadLine()));
        statement.setBoolean(4, todoItem.isDone());
        if (todoItem.getAssigneeId() != 0) {
            statement.setInt(5, todoItem.getAssigneeId());
        } else {
            statement.setNull(5, Types.INTEGER);
        }
    }

    private static TodoItem getTodoItems(ResultSet resultSet) throws SQLException {
        int todoItemId = resultSet.getInt("todo_id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
        boolean done = resultSet.getBoolean("done");
        int assigneeId = resultSet.getInt("assignee_id");

        PeopleDAOCollection peopleDAO =new PeopleDAOCollection();

        Person assignee = peopleDAO.findById(assigneeId);

        return new TodoItem(todoItemId, title, description, deadline, done, assignee, assigneeId);
    }
}
