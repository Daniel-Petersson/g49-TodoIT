package se.lexicon.data.impl;

import se.lexicon.data.IPeopleDAO;
import se.lexicon.model.Person;
import se.lexicon.util.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class PeopleDAOCollection implements IPeopleDAO {


    @Override
    public Person create(Person person) {
        String insertQuery = "INSERT INTO PERSON(first_name, last_name) values (?,?)";
        Connection connection = null;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getLastName());
                int numberOfInsertedRows = statement.executeUpdate();

                if (numberOfInsertedRows > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int generatedPersonId = generatedKeys.getInt(1);
                            person.setId(generatedPersonId);
                            System.out.println("Person created with the id: " + generatedPersonId);
                        }
                    }
                    connection.commit();
                } else {
                    System.out.println("Failed to insert person");
                }
            } catch (SQLException e) {
                System.out.println("Error inserting new person");
                e.printStackTrace();
                if (connection != null) {
                    connection.rollback();
                }
            } finally {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting connection or changing auto-commit mode");
            e.printStackTrace();
        }

        return person;
    }

    @Override
    public Optional<Person> findById(int id) {
        // 1. Define the SQL query to find a person by id
        String findByIdQuery = "SELECT * FROM person WHERE person_id = ?";
        // 2. Declare a Person variable
        Person person = null;
        // 3. Establish a connection to the database
        try (Connection connection = SQLConnection.getConnection();
             // 4. Execute the query and get the result
             PreparedStatement statement = connection.prepareStatement(findByIdQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            // 5. If a person is found, create a Person object
            if (resultSet.next()) {
                int personId = resultSet.getInt("person_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                person = new Person(personId, firstName, lastName);
            }
        } catch (SQLException e) {
            System.out.println("Error getting connection");
        }


        // 6. Return the Person object wrapped in an Optional
        // 7. If no person is found, return an empty Optional
        return Optional.ofNullable(person);
    }

    @Override
    public Collection<Person> findAll() {
        // 1. Define the SQL query to find all persons
        String findAllQuery = "SELECT * FROM person";
        // 2. Declare a List of Person variable
        List<Person> personList = new ArrayList<>();
        // 3. Establish a connection to the database
        try (Connection connection = SQLConnection.getConnection();
             // 4. Execute the query and get the result
             PreparedStatement statement = connection.prepareStatement(findAllQuery)) {
            ResultSet resultSet = statement.executeQuery();
            // 5. For each result, create a Person object and add it to the list
            while (resultSet.next()) {
                int personId = resultSet.getInt("person_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                personList.add(new Person(personId, firstName, lastName));
            }
        } catch (SQLException e) {
            System.out.println("Error getting connection");
        }


        // 6. Return the list of persons
        return personList;
    }

    @Override
    public Collection<Person> find(Predicate<Person> filter) {
        // TODO: 1. Get all persons by calling the findAll() method
        // TODO: 2. Stream the list of persons and filter it using the provided Predicate
        // TODO: 3. Collect the filtered persons into a new list and return it
        return List.of();
    }

    @Override
    public Person update(Person person) {
        // 1. Define the SQL query to update the person's details in the database
        String updateQuery = "UPDATE person SET person_id = ?, first_name = ?, last_name = ?";
        // 2. Declare a Person variable
        // 3. Establish a connection to the database
        Connection connection = null;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setInt(1, person.getId());
            statement.setString(2, person.getFirstName());
            statement.setString(3, person.getLastName());
            // 4. Execute the update query
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update successful");
            } else {
                System.out.println("No person found with the given ID");
            }
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Error updating person");
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println("Error rolling back transaction");
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection");
                }
            }
        }
        // 5. Return the updated Person object
        return person;
    }

    @Override
    public Optional<Boolean> remove(int id) {
        String deleteQuery = "DELETE FROM person WHERE person_id = ?";
        Optional<Person> person = findById(id);
        if (person.isPresent()) {
            try (Connection connection = SQLConnection.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
                    statement.setInt(1, id);
                    int rowsDeleted = statement.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Person deleted successfully: " + id);
                        return Optional.of(true);
                    } else {
                        System.out.println("Person with " + id + " not found");
                        return Optional.of(false);
                    }
                } catch (SQLException e) {
                    System.out.println("Error executing delete query");
                }
            } catch (SQLException e) {
                System.out.println("Error getting connection");
            }
        }
        return Optional.empty();
    }
}
