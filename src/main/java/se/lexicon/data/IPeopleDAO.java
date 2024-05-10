package se.lexicon.data;

import se.lexicon.model.Person;

import java.util.Collection;


public interface IPeopleDAO {
    Person create(Person person);

    Person findById(int id);//int or Person for input?

    Collection<Person> findAll();

    Collection<Person> findByName(String firstName, String lastName);

    Person update(Person person);

    Boolean remove(int id);
}
