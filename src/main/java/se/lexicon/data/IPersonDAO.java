package se.lexicon.data;

import se.lexicon.model.AppUser;
import se.lexicon.model.Person;

import java.util.Collection;
import java.util.Optional;

public interface IPersonDAO {
    Person persist(Person person);

    Optional<Person> find(int id);//int or Person for input?

    Optional<Person> find(String email);

    Collection<Person> find();

    Optional<Person> remove(int id);
}
