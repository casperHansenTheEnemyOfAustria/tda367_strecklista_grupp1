package se.cholmers.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class dbTests {
    DatabaseInterface dbi = DatabaseInterface.getInstance();

    @Test
    void testGetUser() {
        String userID = "1";
        String userName = "Test User";
        String password = "1337";
        String userNick = "Test";
        String userPhoneNumber = "0701234567";
        try {
            dbi.createUser(userName, password, userNick, userPhoneNumber, userNick, userPhoneNumber);
        } catch (RequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(userName, dbi.getUserName(userID));
        assertEquals(userNick, dbi.getUserNick(userID));
        assertEquals(userPhoneNumber, dbi.getUserPhoneNumber(userID));
    }

    @Test
    void testGetUserIDFromNick() {
        String userID = "1";
        String userName = "Test User";
        String password = "1337";
        String userNick = "Test";
        String userPhoneNumber = "0701234567";
        try {
            dbi.createUser(userName, password, userNick, userPhoneNumber, userNick, userPhoneNumber);
        } catch (RequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(userID, dbi.getUserIDFromName(userNick, null));
    }
}
