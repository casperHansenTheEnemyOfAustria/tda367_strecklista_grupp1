package se.cholmers.backend.Interface;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;
import se.cholmers.backend.RequestException;

public interface IDatabaseInterface {

    /**
     * Creates a committee in the database
     * precondition: There has to be a database and the committee has to not exist
     *
     * @param group_name
     * @param year
     * @return committeeID
     * @throws RequestException if the committee already exists
     */
    public String createCommittee(String group_name, String year) throws RequestException;

    /**
     * Creates a product in the database
     * precondition: There has to be a database and the product has to not exist
     * already
     *
     * @param productName
     * @param price
     * @param committee
     * @param amount
     * @throws RequestException
     */
    public String createProduct(String productName, Float price, String committeeid, Integer amount)
            throws RequestException;

    /**
     * Creates a user in the database
     * precondition: There has to be a database and the user has to not exist
     *
     * @param userName
     * @param phoneNumber
     * @param userNick
     * @param password
     * @return
     * @throws RequestException if the user already exists
     *                          <p>
     *                          postcondition: The user is created in the database
     */
    public String createUser(String userName, String phoneNumber, String userNick,
                             String password)
            throws RequestException;

    /**
     * Returns the price of a product
     * precondition: The product has to exist
     *
     * @param id
     * @return price
     * @throws NullPointerException if the product does not exist
     */
    public Float getProductPrice(String id);

    /**
     * Returns the amount of a product
     * precondition: The product has to exist
     *
     * @param id
     * @return amount
     * @throws NullPointerException if the product does not exist
     */
    public int getProductAmount(String id);

    /**
     * Returns name of a product
     * precondition: The product has to exists
     *
     * @param id
     * @return name
     * @throws NullPointerException if the product does not exist
     */
    public String getProductName(String id);

    /**
     * Returns the user id of a user with a name and a password from the database
     * precondition: The user has to exist
     *
     * @param nick
     * @param password
     * @return userID
     * @throws NullPointerException if a user with the given name and password does
     *                              not exist
     */
    public String authenticateUser(String nick, String password) throws RequestException;

    /**
     * Returns the username of a user
     * precondition: The user has to exist
     *
     * @param nick
     * @return username
     * @throws NullPointerException if a user with the given name does not exist
     */
    public String getUserName(String id);

    /**
     * Returns the user nick of a user
     * precondition: The user has to exist
     *
     * @param id
     * @return userNick
     * @throws NullPointerException if a user with the given id does not exist
     */
    public String getUserNick(String id);

    /**
     * Returns the user phone number of a user
     * precondition: The user has to exist
     *
     * @param id
     * @return userPhoneNumber
     * @throws NullPointerException if a user with the given id does not exist
     */
    public String getUserPhoneNumber(String id);

    /**
     * Returns a list of the committees a user is in
     * precondition: The user has to exist
     *
     * @param id
     * @return committees
     * @throws NullPointerException if a user with the given id does not exist
     */
    public List<String> getCommitteesOfUser(String id);

    /**
     * Returns the particular saldo of a user in that committee
     * precondition: The user has to exist and be in the committee and the committee
     * has to exist
     *
     * @param userID
     * @param committeeID
     * @return saldo
     * @throws NullPointerException     if a user with the given id does not exist
     * @throws IllegalArgumentException if the user is not in the committee
     */
    public Float getSaldoFromUserInCommittee(String userID, String committeeID);

    /**
     * Returns the name of a committee
     * precondition: The committee has to exist
     *
     * @param id
     * @return committeeName
     * @throws NullPointerException if a committee with the given id does not exist
     */
    public String getCommitteeName(String id);

    /**
     * Returns the year of a committee
     * precondition: The committee has to exist
     *
     * @param id
     * @return committeeYear
     * @throws NullPointerException if a committee with the given id does not exist
     */
    public String getCommitteeYear(String id);

    /**
     * Returns the products owned by a committee
     * precondition: The committee has to exist
     *
     * @param committeeID
     * @return products
     * @throws NullPointerException if a committee with the given id does not exist
     */
    public List<String> getProductsInCommittee(String committeeID);

    /**
     * Adds a user committee relationship to the database
     * precondition: The user and the committee has to exist
     *
     * @param id
     * @param committeeID
     * @param saldo
     * @throws NullPointerException if a user with the given id does not exist or
     *                              the committee does not exist
     */
    public void putUserInCommittee(String username, String committeeID, Float saldo) throws RequestException;

    /**
     * Updates the saldo of a user in a committee
     * precondition: The user and the committee has to exist and the user has to be
     * in the committee
     *
     * @param id
     * @param committeeId
     * @param saldo
     * @throws NullPointerException     if a user with the given id does not exist
     *                                  or the committee does not exist
     * @throws IllegalArgumentException if the user is not in the committee
     */
    public void updateUserSaldo(String id, String committeeId, String saldo);

    /**
     * Updates the amount of a product
     * precondition: The product has to exist
     *
     * @param productID
     * @param amount
     * @throws NullPointerException if a product with the given id does not exist
     */
    public void updateProductAmount(String productID, String amount);

    /**
     * returns a list of products ordered by a committee at a certain time (an order)
     * @param committeeID
     * @param orderTime
     * @return
     */
    public Map<String, List<String>>  getOrder(String committeeID, LocalDateTime orderTime);
    public List<String> getOrder(String committeeID, String userId, LocalDateTime orderTime)

    /**
     * returns a list of all orders made by a committee ordered in chunks of time
     * @param committeeID
     * @return
     */
    public List<Map<LocalDateTime, String>> getAllOrders(String committeeID);

    public List<Map<LocalDateTime, String>> getAllOrders(String committeeID, String userID);

    /**
     * Adds an order to the database
     * precondition: The committee has to exist
     * @param committeeID
     * @param orderTime
     * @param products
     * @throws NullPointerException if a committee with the given id does not exist
     */
    public void addOrder(String committeeID, String userID, LocalDateTime orderTime, List<String> products);
}