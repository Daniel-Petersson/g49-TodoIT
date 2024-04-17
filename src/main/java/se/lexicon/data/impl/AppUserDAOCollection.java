package se.lexicon.data.impl;

import se.lexicon.data.IAppUserDAO;
import se.lexicon.exception.EntityAlreadyExistsException;
import se.lexicon.model.AppUser;

import java.util.*;

public class AppUserDAOCollection implements IAppUserDAO {
    private final List<AppUser> users = new ArrayList<>();

    @Override
    public AppUser persist(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("AppUser cannot be null");
        Optional<AppUser> optionalAppUser = find(appUser.getUsername());
        optionalAppUser.ifPresent(user -> {
            throw new EntityAlreadyExistsException("User already exist");
        });
        users.add(appUser);
        return appUser;
    }

    @Override
    public Optional<AppUser> find(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

    @Override
    public Collection<AppUser> find() {
        return users;
    }

    @Override
    public Optional<AppUser> remove(String username) {
        Optional<AppUser> userOptional = find(username);
        userOptional.ifPresent(appUser -> users.removeIf(user -> user.getUsername().equalsIgnoreCase(username)));
        return userOptional;
    }
}