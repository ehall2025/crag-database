package org.cragdatabase.data;

import org.cragdatabase.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserLoginJdbcRepositoryTest {

    @Autowired
    UserLoginRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findByUsernameShouldReturnUserWhenExists() {
        User user = repository.findByUsername("tommy@4fingers.com");

        assertNotNull(user);
        assertEquals("tommy@4fingers.com", user.getUsername());
        assertEquals("ROLE_USER", user.getRole().toString());
    }

    @Test
    void findByUsernameShouldReturnNullWhenNotFound() {
        User user = repository.findByUsername("who@who.com");

        assertNull(user);
    }

    @Test
    void findByUsernameShouldAddToListsWhenUserExists() {
        User user = repository.findByUsername("tommy@4fingers.com");

        assertNotNull(user.getTodoList());
        assertNotNull(user.getTickList());
    }

    @Test
    void createUserShouldReturnUserWithId() {
        User newUser = new User(0, "new@user.com", "password", "ROLE_USER");
        User created = repository.createUser(newUser);

        assertNotNull(created);
        assertTrue(created.getId() > 0);
        assertEquals("new@user.com", created.getUsername());
    }

    @Test
    void createUserShouldReturnNullWhenEmailTaken() {
        User duplicate = new User(0, "tommy@4fingers.com", "password", "ROLE_USER");
        User result = repository.createUser(duplicate);

        assertNull(result);
    }

    @Test
    void registerAdminAccountShouldReturnTrueWhenUserExists() {
        User user = repository.findByUsername("tommy@4fingers.com");
        boolean result = repository.registerAdminAccount(user.getId());

        assertTrue(result);
    }

    @Test
    void registerAdminAccountShouldReturnFalseWhenUserNotFound() {
        boolean result = repository.registerAdminAccount(999);

        assertFalse(result);
    }
}