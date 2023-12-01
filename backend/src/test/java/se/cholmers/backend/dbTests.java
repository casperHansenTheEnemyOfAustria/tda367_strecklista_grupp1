package se.cholmers.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.Assert;

import se.cholmers.backend.Interface.IDatabaseInterface;

public class dbTests {
    IDatabaseInterface dbi = newDatabaseInterface.getInstance();

    @Test
    void testGetUser() {
        String userID = "1";
        String userName = "Test User";
        String password = "1337";
        String userNick = "Test";
        String userPhoneNumber = "0701234567";
        try {
            dbi.createUser(userName, userPhoneNumber, userNick, password);
            assertEquals(userName, dbi.getUserName(userID));
            assertEquals(userNick, dbi.getUserNick(userID));
            assertEquals(userPhoneNumber, dbi.getUserPhoneNumber(userID));
        } catch (RequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (!(e.getMessage().equals("User already exists or something idk")))
                Assert.fail("Test failed" + e.getMessage());
        }
    }

    

    @Test
    void testGetUserIDFromNick() {
        String userName = "Test User";
        String password = "1337";
        String userNick = "Test";
        String userPhoneNumber = "0701234567";
        try {
            String id = dbi.createUser(userName, userPhoneNumber, userNick, password);
            assertEquals(id, dbi.authenticateUser(userNick, password));
        } catch (RequestException e) {
            e.printStackTrace();
            Assert.fail("Test failed" + e.getMessage());
        }
    }

    @Test 
    void deleteFromDatabase() {
        throw new UnsupportedOperationException();
    }
}
