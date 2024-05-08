package se.lexicon.data.impl;

import se.lexicon.data.IPeopleDAO;
import se.lexicon.model.Person;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class PeopleDAOCollection implements IPeopleDAO {
    @Override
    public Person persist(Person person) {
        return null;
    }

    @Override
    public Optional<Person> find(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Person> find(String email) {
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
