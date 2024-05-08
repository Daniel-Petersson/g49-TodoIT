package se.lexicon.data.impl;

import se.lexicon.data.IPeopleDAO;
import se.lexicon.model.Person;
import se.lexicon.util.SQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PeopleDAOCollection implements IPeopleDAO {

    @Override
    public Person create(Person person) {
        String insertQuery = "INSERT INTO PERSON(first_name, last_name) values (?,?)";
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
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
            } else {
                System.out.println("Failed to insert person");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting new person");
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person findById(int id) {
        String findByIdQuery = "SELECT * FROM person WHERE person_id = ?";
        Person person = null;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(findByIdQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int personId = resultSet.getInt("person_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                person = new Person(personId, firstName, lastName);
            }
        } catch (SQLException e) {
            System.out.println("Error getting connection");
        }
        return person;
    }

    @Override
    public Collection<Person> findAll() {
        String findAllQuery = "SELECT * FROM person";
        List<Person> personList = new ArrayList<>();
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(findAllQuery);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int personId = resultSet.getInt("person_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                personList.add(new Person(personId, firstName, lastName));
            }
        } catch (SQLException e) {
            System.out.println("Error getting connection");
        }
        return personList;
    }

    @Override
    public Collection<Person> findByName(String firstName, String lastName) {
        String findByNameQuery = "SELECT * FROM person WHERE first_name = ? AND last_name = ?";
        List<Person> personsFound = new ArrayList<>();
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(findByNameQuery)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int personId = resultSet.getInt(1);
                    String foundFirstName = resultSet.getString(2);
                    String foundLastName = resultSet.getString(3);
                    personsFound.add(new Person(personId, foundFirstName, foundLastName));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting connection");
        }
        return personsFound;
    }

    @Override
    public Person update(Person person) {
        String updateQuery = "UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?";
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setInt(3, person.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update successful");
            } else {
                System.out.println("No person found with the given ID");
            }
        } catch (SQLException e) {
            System.out.println("Error updating person");
        }
        return person;
    }

    @Override
    public Boolean remove(int id) {
        String deleteQuery = "DELETE FROM person WHERE person_id = ?";
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Person deleted successfully: " + id);
                return true;
            } else {
                System.out.println("Person with " + id + " not found");
            }
        } catch (SQLException e) {
            System.out.println("Error executing delete query");
        }
        return false;
    }
}