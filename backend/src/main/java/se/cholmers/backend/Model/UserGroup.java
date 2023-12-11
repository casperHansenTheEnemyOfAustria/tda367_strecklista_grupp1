package se.cholmers.backend.Model;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import se.cholmers.backend.Model.Interfaces.IOrder;
import se.cholmers.backend.Model.Interfaces.IOrderHistory;
import se.cholmers.backend.Model.Interfaces.IProduct;
import se.cholmers.backend.Model.Interfaces.IUserGroup;
import se.cholmers.backend.RequestException;
import se.cholmers.backend.newDatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;

class UserGroup implements IUserGroup{
    private IDatabaseInterface dbi = newDatabaseInterface.getInstance();
    private Set<IProduct> products = new HashSet<>();
    private Year year;
    private String name;
    private String groupID;
    private IOrderHistory orderHistory;

    /**
     * Creates a new UserGroup with a given name and year.
     * this is only used for testing
     * 
     * @param name
     * @param year
     * @throws RequestException
     */
    public UserGroup(String name, Year year) throws RequestException {
        this.name = name;
        this.year = year;

        //TODO: Remove and handle in DB
        this.groupID = UUID.randomUUID().toString();

        this.orderHistory = new OrderHistory(groupID);
        // code that initializes the object from the database
    }

    /**
     * Fetches a UserGroup from the database given a groupID
     * 
     * @param groupID
     * @throws RequestException
     */
    public UserGroup(String groupID) throws RequestException {
        String name = dbi.getCommitteeName(groupID);
        Year year = Year.parse("20" + dbi.getCommitteeYear(groupID));
        
        this.name = name;
        this.year = year;
        this.groupID = groupID;
        this.orderHistory = new OrderHistory(groupID);

    }

    @Override
    public Set<IProduct> getProducts() {
        List<String> productsFromDB = dbi.getProductsInCommittee(groupID);
        for (String productID : productsFromDB) {
            
            products.add(new Product(productID, groupID));
        }

        return products;
    }

    @Override
    public void addProduct(String name, Float cost) throws RequestException {
        products.add(new Product(name, cost, groupID));
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
    @Override
    public List<IOrder> getOrderHistory(String user) throws RequestException {
        return orderHistory.getOrderHistory(user);
    }

    @Override
    public String getID() {
        return this.groupID;
    }

}