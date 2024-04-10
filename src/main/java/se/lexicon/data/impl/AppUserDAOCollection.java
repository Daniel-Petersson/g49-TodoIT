package se.lexicon.data.impl;

import se.lexicon.data.IAppUserDAO;
import se.lexicon.model.AppUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * This class implements the IAppUserDAO interface and provides concrete implementations for each of the operations that can be performed on AppUser objects.
 */
public class AppUserDAOCollection implements IAppUserDAO {
    private final List<AppUser> users = new ArrayList<>();

    /**
     * Method to persist an AppUser object.
     * @param appUser The AppUser object to be persisted.
     * @return The persisted AppUser object.
     * @throws IllegalArgumentException If the input AppUser is null or already exists in the list.
     */
    @Override
    public AppUser persist(AppUser appUser) {
        if (appUser == null) {
            throw new IllegalArgumentException("AppUser cannot be null");
        }
        Optional<AppUser> optionalAppUser = findByUsername(appUser.getUsername());
        if (optionalAppUser.isPresent()) {
            throw new IllegalArgumentException("User already exist");
        }
        users.add(appUser);
        return appUser;
    }

    /**
     * Method to find an AppUser by username.
     * @param username The username of the AppUser to be found.
     * @return An Optional containing the found AppUser, or an empty Optional if no AppUser was found.
     */
    @Override
    public Optional<AppUser> findByUsername(String username) {
        for (AppUser user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    /**
     * Method to find all AppUser objects.
     * @return A Collection containing all AppUser objects.
     */
    @Override
    public Collection<AppUser> findAll() {
        return new ArrayList<>(users); //In general, if you want to prevent external code from modifying your class's internal state, it's a good practice to return a new copy of the collection.
    }

    /**
     * Method to remove an AppUser by username.
     * @param username The username of the AppUser to be removed.
     * @throws IllegalArgumentException If the AppUser is not found.
     */
    @Override
    public void remove(String username) {
        Optional<AppUser> optionalAppUser = findByUsername(username);
        if (!optionalAppUser.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        users.remove(optionalAppUser.get());
    }
}