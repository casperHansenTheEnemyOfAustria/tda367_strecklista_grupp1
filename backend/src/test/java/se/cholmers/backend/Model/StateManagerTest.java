package se.cholmers.backend.Model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import se.cholmers.backend.RequestException;
import se.cholmers.backend.newDatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;

public class StateManagerTest {

    private IDatabaseInterface dbi = newDatabaseInterface.getInstance();
    private StateManager sm = StateManager.getInstance();

    @Test
    void ableToLogin() {
        /* try {
            String id = dbi.createUser("test", "test", "test", "test");
        } catch (RequestException e) {
            e.printStackTrace();
        } */

        assertDoesNotThrow(() -> sm.login("test", "test"), "Login should not throw an exception");

        try {
            String id = dbi.authenticateUser("test", "test");
            dbi.delete("users", new Pair<>("id", id));
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }

    @Test
    void ableToLogOut() {
        throw new UnsupportedOperationException();
    }





}
