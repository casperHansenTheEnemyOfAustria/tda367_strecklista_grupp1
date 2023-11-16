package se.cholmers.backend.Model;

import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UserGroup {
    private Set<Product> products = new HashSet<>();
    private Year year;
    private String name;
    private String groupID;
    private OrderHistory orderHistory;

    /**
     * Creates a new UserGroup with a given name and year.
     * 
     * @param name
     * @param year
     */
    public UserGroup(String name, Year year) {
        this.name = name;
        this.year = year;
        this.orderHistory = new OrderHistory();
        this.groupID = UUID.randomUUID().toString();
        // code that initializes the object from the database
    }

    /**
     * Fetches a UserGroup from the database given a groupID
     * 
     * @param groupID
     */
    public UserGroup(String groupID) {
        // code that initializes the object from the database
    }

    /**
     * First updates the products from the database.
     * 
     * @return the set of products in the usergroup
     */
    public Set<Product> getProducts() {
        return products;
    }

    /**
     * Creates a new product and adds to the existing set of products.
     * Temp solution until we have a working database interface.
     * 
     * @param name
     * @param cost
     */
    public void addProduct(String name, Float cost) {
        products.add(new Product(name, cost, groupID));
    }

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
     */
    public List<Order> getOrderHistory() {
        return orderHistory.getOrderHistory();
    }

    /**
     * 
     * @return the groupID of a certain group.
     */
    public String getID() {
        return this.groupID;
    }
}