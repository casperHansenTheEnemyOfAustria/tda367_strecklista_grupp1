package se.cholmers.backend.Model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;

import io.micrometer.core.ipc.http.HttpSender.Request;
import javafx.util.Pair;
import se.cholmers.backend.RequestException;
import se.cholmers.backend.DatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;

public class StateManagerTest {

    private IDatabaseInterface dbi = DatabaseInterface.getInstance();
    private StateManager sm = StateManager.getInstance();

    @Test
    void ableToLogin() {
        try {
            String id = dbi.createUser("test", "test", "test", "test");
            assertDoesNotThrow(() -> sm.login("test", "test"), "Login should not throw an exception");
        } catch (RequestException e) {
            e.printStackTrace();
        }

        

        try {
            String id = dbi.authenticateUser("test", "test");
            dbi.delete("users", new Pair<>("id", id));
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }

    @Test
    void ableToLogOut() {
        try {
            String id = dbi.createUser("test", "test", "test", "test");
            String stateID = sm.login("test", "test");
            assertDoesNotThrow(() -> sm.logout(stateID), "Logout should not throw an exception");
            dbi.delete("users", new Pair<>("id", id)); // Delete the user from the database
        } catch (RequestException e) {
            e.printStackTrace();
        }
        
    }

    

    @Test
    void notAbleToLogoutWithoutLogin() {
        try {
            String id = dbi.createUser("test", "test", "test", "test");
            sm.login("test", "test");
            sm.logout("test");
            assertThrows(RequestException.class, () -> sm.logout("test"), "Logout should throw a RequestException");
            dbi.delete("users", new Pair<>("id", id)); // Delete the user from the database
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }

}
