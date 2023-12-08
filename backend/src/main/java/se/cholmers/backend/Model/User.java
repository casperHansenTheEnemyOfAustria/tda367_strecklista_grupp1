package se.cholmers.backend.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import se.cholmers.backend.RequestException;
import se.cholmers.backend.newDatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;

/**
 * This class represents a user in the system. It contains information about the
 * user such as their name, nickname and phone number
 */
class User {
    private String id;
    private String name;
    private String nick;
    private String phoneNumber;
    private Map<String, Float> saldo = new HashMap<>();
    private Set<UserGroup> groups = new HashSet<>();
    private IDatabaseInterface dbi = newDatabaseInterface.getInstance();
    private OrderHistory orderHistory;

    /**
     * This is the constructor for when recreating users from the database
     * 
     * @param userID
     * @throws RequestException
     */
    public User(String userID) throws RequestException {
        this.id = userID;
        this.name = dbi.getUserName(userID);
        this.nick = dbi.getUserNick(userID);
        this.phoneNumber = dbi.getUserPhoneNumber(userID);
        addGroupsFromDatabase();
        this.orderHistory = new OrderHistory(userID);
    }

    /**
     * This method adds the user's groups to the user from the database
     * 
     * @param userID
     * @throws RequestException
     * 
     */
    private void addGroupsFromDatabase() throws RequestException {
        // TODO add a catch for if there are no groups
        List<String> comitteeIds = dbi.getCommitteesOfUser(id);
        for (String param : comitteeIds) {
            groups.add(new UserGroup(param));
        }
    }

    /**
     * This is the constructor
     * it should be called when a new user is created and also add the
     * user's group to it read from the database
     * 
     * @param name
     * @param nick
     * 
     * @throws NullPointerException if the input is null
     */
    public User(String name, String nick, String password) throws RequestException{
        this.name = name;
        this.nick = nick;
        this.id = dbi.authenticateUser(nick, password);
        addGroupsFromDatabase();
        this.orderHistory = new OrderHistory(this.id);
        

    }

    public void addUserToGroup(UserGroup group) {
        try{
            dbi.putUserInCommittee(id, group.getID().toString(), 0f);
        } catch (RequestException e) {
            System.out.println(e.getMessage());
        }
        groups.add(group);
    }

    /**
     * 
     * @param groupID
     * @return the saldo of the user in the context of its groupID
     * 
     * @throws IllegalArgumentException if the user is not a member of the group
     * @throws NullPointerException     if the user is not a member of any group
     */
    public Float getSaldo(String groupID) {
        Float saldoFromDB = dbi.getSaldoFromUserInCommittee(id, groupID);
        saldo.put(groupID, saldoFromDB);
        return saldo.get(groupID);
    }

    /**
     * Updates the user saldo based on the price and number of the products it wants
     * to purchase.
     * Negative saldo is permitted.
     * 
     * @param product
     * @param numberOfProducts
     * @throws RequestException
     */
    public void purchaseItem(Product product, Integer numberOfProducts) throws RequestException {
        Float currentSaldo = saldo.get(product.getGroupID());
        currentSaldo -= product.getCost() * numberOfProducts;
        saldo.put(product.getGroupID(), currentSaldo);

        try{
            dbi.updateUserSaldo(id, product.getGroupID(), currentSaldo.toString());
            product.decreaseAmount(numberOfProducts);
        }catch(NullPointerException e){
            throw new RequestException(id + "does not exist or" + product.getGroupID() + "does not exist");
        }catch(IllegalArgumentException e){
            throw new RequestException(id + "is not in " + product.getGroupID());
        }
        
    
    }

    /**
     * 
     * @return returns a set of all products the user has access to in all its
     *         usergorups
     * @throws RequestException
     * @throws NullPointerException if the user is not a member of any group
     */
    public Set<Product> getAllProducts() throws RequestException {
        Set<Product> allProducts = new HashSet<>();
        addGroupsFromDatabase();
        for (UserGroup userGroup : groups) {
            allProducts.addAll(userGroup.getProducts());
        }
        return allProducts;
    }

    /**
     * 
     * @param productID
     * @return the product with the given productID logged in its usergroup's
     *         database
     * @throws RequestException
     * @throws NullPointerException if the user is not a member of any group
     */
    public Product getProduct(String productID) throws RequestException {
        for (Product product : getAllProducts()) {
            if (product.getID() == productID) {
                return product;
            }
        }
        return null;
    }

        //TODO add to db
    /**
     * Adds an order to the group's orderhistory
     * 
     * @param order
     */
    public void addOrderToHistory(Order order) {
        orderHistory.addOrderToHistory(order);
    }

    /**
     * First updates the history from the database.
     * 
     * @return the order history for a group
     * @throws RequestException
     */
    public List<Order> getOrderHistory() throws RequestException {
        
        return orderHistory.getOrderHistory();
    }

    public String getID() {
        return id;
    }

}