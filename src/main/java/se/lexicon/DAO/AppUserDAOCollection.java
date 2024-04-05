package se.lexicon.DAO;

import se.lexicon.Model.AppUser;

import java.util.ArrayList;
import java.util.Collection;

public class AppUserDAOCollection implements IAppUserDAO {
    private final ArrayList<AppUser> users;

    private AppUserDAOCollection() {
        users = new ArrayList<>();
    }

    @Override
    public AppUser persist(AppUser appUser) {
        if (appUser == null) {
            throw new IllegalArgumentException("AppUser cannot be null");
        }

        for (AppUser existingUser : users) {
            if (existingUser.getUsername().equalsIgnoreCase(appUser.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }
        }
        users.add(appUser);
        return appUser;
    }

    @Override
    public AppUser findByUsername(String username) {
        for (AppUser user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null; //Return if no match found
    }

    @Override
    public Collection<AppUser> findAll() {
        return new ArrayList<>(users); //In general, if you want to prevent external code from modifying your class's internal state, it's a good practice to return a new copy of the collection.
    }

    @Override
    public void remove(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
    }
}
