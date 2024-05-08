package se.lexicon.data;

import se.lexicon.model.Person;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public interface IPeopleDAO {
    Person create(Person person);

    Person findById(int id);//int or Person for input?

    Collection<Person> findAll();

    Collection<Person> findByName(String firstName, String lastName);

    Person update(Person person);

    Boolean remove(int id);
}
