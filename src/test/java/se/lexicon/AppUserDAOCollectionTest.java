package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.data.impl.AppUserDAOCollection;
import se.lexicon.util.AppRole;
import se.lexicon.model.AppUser;
import se.lexicon.exception.EntityAlreadyExistsException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AppUserDAOCollectionTest {
    private AppUserDAOCollection testObject;
    private AppUser testUser;

    @BeforeEach
    void setUp() {
        testObject = new AppUserDAOCollection();
        testUser = new AppUser("testUser","test", AppRole.ROLE_APP_USER);

    }

    @Test
    public void persist_ShouldPersistUser_WhenUserDoesNotExist() {
        AppUser newUser = new AppUser("newUser","test", AppRole.ROLE_APP_USER);
        AppUser persistedUser = testObject.persist(newUser);
        assertEquals(newUser, persistedUser);
        assertTrue(testObject.find(newUser.getUsername()).isPresent());
    }

    @Test
    public void persist_ShouldThrowException_WhenUserAlreadyExists() {
        AppUser existingUser = new AppUser("existingUser","test", AppRole.ROLE_APP_USER);
        testObject.persist(existingUser);
        assertThrows(EntityAlreadyExistsException.class, () -> testObject.persist(existingUser));
    }

    @Test
    public void persist_ShouldThrowException_WhenUserIsNull() {
        assertThrows(IllegalArgumentException.class, () -> testObject.persist(null));
    }

    @Test
    public void find_ShouldReturnUser_WhenUserExists() {
        AppUser existingUser = new AppUser("existingUser","test", AppRole.ROLE_APP_USER);
        testObject.persist(existingUser);
        Optional<AppUser> foundUser = testObject.find(existingUser.getUsername());
        assertTrue(foundUser.isPresent());
        assertEquals(existingUser, foundUser.get());
    }

    @Test
    public void find_ShouldReturnEmptyOptional_WhenUserDoesNotExist() {
        Optional<AppUser> foundUser = testObject.find("nonExistingUser");
        assertFalse(foundUser.isPresent());
    }

    @Test
    public void remove_ShouldRemoveUser_WhenUserExists() {
        AppUser existingUser = new AppUser("existingUser","test", AppRole.ROLE_APP_USER);
        testObject.persist(existingUser);
        Optional<AppUser> removedUser = testObject.remove(existingUser.getUsername());
        assertTrue(removedUser.isPresent());
        assertEquals(existingUser, removedUser.get());
        assertFalse(testObject.find(existingUser.getUsername()).isPresent());
    }

    @Test
    public void remove_ShouldReturnEmptyOptional_WhenUserDoesNotExist() {
        Optional<AppUser> removedUser = testObject.remove("nonExistingUser");
        assertFalse(removedUser.isPresent());
    }
}