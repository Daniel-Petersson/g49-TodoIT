package se.lexicon.data.impl;

import se.lexicon.data.IPeopleDAO;
import se.lexicon.model.Person;
import se.lexicon.util.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            boolean oldAutoCommitMode = connection.getAutoCommit();
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
                    connection.setAutoCommit(oldAutoCommitMode);
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
        return Optional.empty();
    }

    @Override
    public Optional<Person> findAll() {
        return Optional.empty();
    }

    @Override
    public Collection<Person> find(Predicate<Person> filter) {
        return List.of();
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public Optional<Person> remove(int id) {
        return Optional.empty();
    }
}
