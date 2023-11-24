package se.cholmers.backend.Interface;

import java.util.List;
import java.util.Map;

import se.cholmers.backend.RequestException;

public interface IDatabaseInterface {

    public void closeConnection();

    public void executeUpdate(String query, List<Object> params);

    public List<Map<String, Object>> executeQuery(String sql, List<Object> parameters);

    public void createCommittee(String group_name, String year);

    public void createProduct(String productName, String price, String committee, String amount)
            throws RequestException;

    public String createUser(String userName, String userNick, String phoneNumber, String committeeID, String saldo)
            throws RequestException;

    public Float getProductPrice(String id);

    public int getProductAmount(String id);

    public String getProductName(String id);

    public String getUserIDFromName(String nick, String password);

    public String getUserName(String id);

    public String getUserNick(String id);

    public String getUserPhoneNumber(String id);

    public List<String> getCommitteesOfUser(String id);

    public Float getSaldoFromUserInCommittee(String userID, String committeeID);

    public String getCommitteeName(String id);

    public String getCommitteeYear(String id);

    public List<String> getProductsInCommittee(String committeeID);

    public void putUserInCommittee(String id, String committeeID, String saldo);

    public void updateUserSaldo(String id, String committeeId, String saldo);

    public void updateProductAmount(int productID, String amount);
}