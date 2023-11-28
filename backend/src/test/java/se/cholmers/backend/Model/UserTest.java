package se.cholmers.backend.Model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void ableToCreateUser() {
        User tUser = new User("Test", "Test", "test");
    }

    @Test
    void unableToCreateUserWithNullName() {
        assertThrows(NullPointerException.class, () -> new User(null, "Test", "test"));
    }

    @Test
    void unableToCreateUserWithNullNickname() {
        assertThrows(NullPointerException.class, () -> new User(null, "Test", "test"));
    }

    @Test
    void unableToCreateUserWithNullNameAndNickname() {
        assertThrows(NullPointerException.class, () -> new User(null, "Test", "test"));
    }
}
