package se.cholmers.backend.Model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import io.micrometer.core.ipc.http.HttpSender.Request;
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
        assertThrows(RequestException.class, () -> new User(null, "Test", "test"));
    }

    @Test
    void unableToCreateUserWithNullNickname() {
        assertThrows(RequestException.class, () -> new User(null, "Test", "test"));
    }

    @Test
    void unableToCreateUserWithNullNameAndNickname() {
        assertThrows(RequestException.class, () -> new User(null, "Test", "test"));
    }
    
}