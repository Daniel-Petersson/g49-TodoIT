package se.lexicon.data;


import se.lexicon.model.Person;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public interface IPersonDAO {
    Person persist(Person person);

    Optional<Person> find(int id);//int or Person for input?

    Optional<Person> find(String email);

    Collection<Person> find(Predicate<Person> filter);

    Optional<Person> remove(int id);
}
