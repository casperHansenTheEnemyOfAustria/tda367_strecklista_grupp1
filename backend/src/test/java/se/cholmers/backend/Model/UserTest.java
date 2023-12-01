package se.cholmers.backend.Model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import se.cholmers.backend.RequestException;

public class UserTest {

    @Test
    void ableToCreateUser() {
        try {
            User tUser = new User("Test", "Test", "test");
        } catch (RequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
