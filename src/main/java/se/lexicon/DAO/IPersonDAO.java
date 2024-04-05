package se.lexicon.DAO;
import se.lexicon.Model.Person;

import java.util.Collection;

public interface IPersonDAO {
    Person persist(Person person);
    Person findById(int id);//int or Person for input?
    Person findByEmail(String email);
    Collection<Person> findAll();
    void remove(int id);
}
