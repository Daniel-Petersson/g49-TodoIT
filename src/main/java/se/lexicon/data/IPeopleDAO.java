package se.lexicon.data;

import se.lexicon.model.Person;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public interface IPeopleDAO {
    Person create(Person person);

    Optional<Person> findById(int id);//int or Person for input?

    Collection<Person> findAll();

    Collection<Person> find(Predicate<Person> filter);

    Person update(Person person);

    Optional<Boolean> remove(int id);
}
