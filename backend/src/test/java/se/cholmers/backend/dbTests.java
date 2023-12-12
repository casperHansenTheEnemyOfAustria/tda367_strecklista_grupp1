package se.cholmers.backend;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;

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
            if (dbi.authenticateUser(userNick, password) != null) {
                dbi.delete("users", new Pair<String,String>("user_nick", userNick));
            }
                String id = dbi.createUser(userName, userPhoneNumber, userNick, password);
            //assertEquals(id, dbi.authenticateUser(userNick, password));
        } catch (RequestException e) {
            //e.printStackTrace();
            Assert.fail("Test failed" + e.getMessage());
        }
    }

    @Test 
    void deleteFromDatabase() {
        String userName = "Test User";
        String password = "1337";
        String userNick = "Test";
        String userPhoneNumber = "0701234567";

        try {
            String id = dbi.createUser(userName, userPhoneNumber, userNick, password);
        } catch (RequestException e) {
            e.printStackTrace();
        }
        assertDoesNotThrow(() -> dbi.delete("users", new Pair<String,String>("user_nick", userNick)));
        assertThrows(RequestException.class, () -> dbi.authenticateUser(userNick, password));
    }

    @Test
    void testUpdateUserSaldo() {
        String userName = "Test User";
        String password = "1337";
        String userNick = "Test";
        String userPhoneNumber = "0701234567";
        String committeeName = "Test Committee";
        String committeeYear = "22";

        
        //Potentially null
        String userID = null;
        
        try {
            userID = dbi.createUser(userName, userPhoneNumber, userNick, password);
        } catch (RequestException e) {
            try {
                userID = dbi.authenticateUser(userNick, password);
            } catch (RequestException e1) {
                Assert.fail();
            }
        }
        try {
            dbi.delete("Committes", new Pair<String,String>("committee_name", committeeName));
        } catch (RequestException e){
            // Do nothing
        }
        String committeeID = null;
        try {
            committeeID = dbi.createCommittee(committeeName, committeeYear);
    
        } catch (RequestException e) {
                Assert.fail();
        }
        try {
                dbi.updateUserSaldo(userID, committeeID, "100");
                assertEquals(100, dbi.getSaldoFromUserInCommittee(userID, committeeID));
            } catch (NullPointerException np) {
                np.printStackTrace();
                Assert.fail("Test failed" + np.getMessage());
            } catch (IllegalArgumentException iae) {
                iae.printStackTrace();
                Assert.fail("Test failed" + iae.getMessage());
            }

    }

    
}
