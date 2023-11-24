package se.cholmers.backend.Interface;

import java.util.List;

import se.cholmers.backend.DatabaseInterface;

public interface IDatabaseInterface {
    public DatabaseInterface getInstance();

    

    public String getUserName(String userID);

    public String getUserNick(String userID);

    public String getUserPhoneNumber(String userID);

    public List<String> getCommitteesOfUser(String userID);
}
