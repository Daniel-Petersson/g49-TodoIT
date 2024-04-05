package se.lexicon.DAO;

import se.lexicon.App;
import se.lexicon.Model.AppUser;

import java.util.Collection;

public interface IAppUserDAO {
    AppUser persist(AppUser appUser);
    AppUser findByUsername(String username);
    Collection<AppUser> findAll();
    void remove(String username);

}
