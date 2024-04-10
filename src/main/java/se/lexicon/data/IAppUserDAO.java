package se.lexicon.data;

import se.lexicon.model.AppUser;

import java.util.Collection;
import java.util.Optional;

public interface IAppUserDAO {
    AppUser persist(AppUser appUser);
    Optional<AppUser> find(String username);
    Collection<AppUser> find();
    void remove(String username);

}
