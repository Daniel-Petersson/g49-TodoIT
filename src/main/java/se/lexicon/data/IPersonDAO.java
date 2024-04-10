package se.lexicon.data;
import se.lexicon.model.Person;

import java.util.Collection;
import java.util.Optional;

public interface IPersonDAO {
    Person persist(Person person);
    Optional<Person> findById(int id);//int or Person for input?
    Optional<Person> findByEmail(String email);
    Collection<Person> findAll();
    void remove(int id);
}
