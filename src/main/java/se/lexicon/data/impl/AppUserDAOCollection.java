package se.lexicon.data.impl;

import se.lexicon.data.IAppUserDAO;
import se.lexicon.exception.EntityAlreadyExistsException;
import se.lexicon.model.AppUser;

import java.util.*;

/**
 * This class implements the IAppUserDAO interface and provides concrete implementations for each of the operations that can be performed on AppUser objects.
 */
public class AppUserDAOCollection implements IAppUserDAO {
    private final Map<Integer, AppUser> users = new HashMap<>();

    @Override
    public AppUser persist(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("AppUser cannot be null");
        Optional<AppUser> optionalAppUser = find(appUser.getUsername());
        optionalAppUser.ifPresent(user -> {
            throw new EntityAlreadyExistsException("User already exist");
        });
        return appUser;
    }

    @Override
    public Optional<AppUser> find(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

    @Override
    public Collection<AppUser> find() {
        return users.values();
    }

    @Override
    public Optional<AppUser> remove(String username) {
        Optional<AppUser> userOptional = find(username);
        userOptional.ifPresent(appUser -> {
            users.values().removeIf(user -> user.getUsername().equalsIgnoreCase(username));
        });
        return userOptional;
    }
}