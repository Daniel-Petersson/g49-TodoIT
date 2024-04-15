package se.lexicon.data.impl;

import se.lexicon.data.IAppUserDAO;
import se.lexicon.data.sequencers.AppUserSequencer;
import se.lexicon.exception.EntityAlreadyExistsException;
import se.lexicon.model.AppUser;

import java.util.*;

/**
 * This class implements the IAppUserDAO interface and provides concrete implementations for each of the operations that can be performed on AppUser objects.
 */
public class AppUserDAOCollection implements IAppUserDAO {
    private final Map<Integer, AppUser> users = new HashMap<>();

    /**
     * Method to persist an AppUser object.
     *
     * @param appUser The AppUser object to be persisted.
     * @return The persisted AppUser object.
     * @throws IllegalArgumentException If the input AppUser is null or already exists in the list.
     */
    @Override
    public AppUser persist(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("AppUser cannot be null");
        Optional<AppUser> optionalAppUser = find(appUser.getUsername());
        optionalAppUser.ifPresent(user -> {
            throw new EntityAlreadyExistsException("User already exist");
        });
        int id = AppUserSequencer.nextId();
        users.put(id, appUser);
        return appUser;
    }

    /**
     * Method to find an AppUser by username.
     *
     * @param username The username of the AppUser to be found.
     * @return An Optional containing the found AppUser, or an empty Optional if no AppUser was found.
     */
    @Override
    public Optional<AppUser> find(String username) {
        for (AppUser user : users.values())
            if (user.getUsername().equalsIgnoreCase(username)) return Optional.of(user);
        return Optional.empty();
    }

    /**
     * Method to find all AppUser objects.
     *
     * @return A Collection containing all AppUser objects.
     */
    @Override
    public Collection<AppUser> find() {
        return new ArrayList<>(users.values()); //In general, if you want to prevent external code from modifying your class's internal state, it's a good practice to return a new copy of the collection.
    }

    /**
     * Method to remove an AppUser by username.
     *
     * @param username The username of the AppUser to be removed.
     * @throws IllegalArgumentException If the AppUser is not found.
     */
    @Override
    public Optional<AppUser> remove(String username) {
        Optional<AppUser> userOptional = find(username);
        userOptional.ifPresent(appUser -> {
            AppUser user = appUser;
            users.entrySet().removeIf(entry -> entry.getValue().equals(user));

        });
        return userOptional;
    }
}