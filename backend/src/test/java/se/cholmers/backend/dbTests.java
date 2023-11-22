package se.cholmers.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class dbTests {
    DatabaseInterface dbi = DatabaseInterface.getInstance();

    @Test
    void testGetUser() {
        String userID = "1";
        String userName = "Test User";
        String userNick = "Test";
        String userPhoneNumber = "0701234567";
        dbi.createUser(userName, userNick, userPhoneNumber, userNick, userPhoneNumber);
        assertEquals(userName, dbi.getUserName(userID));
        assertEquals(userNick, dbi.getUserNick(userID));
        assertEquals(userPhoneNumber, dbi.getUserPhoneNumber(userID));
    }

    @Test
    void testGetUserIDFromNick(){
        String userID = "1";
        String userName = "Test User";
        String userNick = "Test";
        String userPhoneNumber = "0701234567";
        dbi.createUser(userName, userNick, userPhoneNumber, userNick, userPhoneNumber);
        assertEquals(userID, dbi.getUserIDFromName(userNick, null));
    }
}
