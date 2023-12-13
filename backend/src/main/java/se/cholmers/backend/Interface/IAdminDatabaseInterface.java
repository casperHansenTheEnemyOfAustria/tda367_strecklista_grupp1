package se.cholmers.backend.Interface;

import se.cholmers.backend.RequestException;

public interface IAdminDatabaseInterface {
    public String createUser(String userName, String phoneNumber, String userNick, String password) throws RequestException;

    public String createCommittee(String group_name, String year) throws RequestException;
    
    public void putUserInCommittee(String username, String committeeID, float saldo) throws RequestException;

    public String createProduct(String productName, Float price, String committeeid, Integer amount) throws RequestException;

    public void updateProductAmount(String productID, Integer amount);

    public void updateUserSaldo(String id, String committeeId, String saldo) throws NullPointerException, IllegalArgumentException;
}