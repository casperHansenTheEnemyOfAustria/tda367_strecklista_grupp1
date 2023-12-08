package se.cholmers.backend.Model;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import se.cholmers.backend.DatabaseInterface;
import se.cholmers.backend.RequestException;
import se.cholmers.backend.newDatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;

class UserGroup {
    private IDatabaseInterface dbi = newDatabaseInterface.getInstance();
    private Set<Product> products = new HashSet<>();
    private Year year;
    private String name;
    private String groupID;
    private OrderHistory orderHistory;

    /**
     * Creates a new UserGroup with a given name and year.
     * this is only used for testing
     * 
     * @param name
     * @param year
     */
    public UserGroup(String name, Year year) {
        this.name = name;
        this.year = year;
        

        //TODO: Remove and handle in DB
        this.groupID = UUID.randomUUID().toString();

        this.orderHistory = new OrderHistory(this.groupID);
        // code that initializes the object from the database
    }

    /**
     * Fetches a UserGroup from the database given a groupID
     * 
     * @param groupID
     */
    public UserGroup(String groupID) {
        String name = dbi.getCommitteeName(groupID);
        Year year = Year.parse("20" + dbi.getCommitteeYear(groupID));
        
        this.name = name;
        this.year = year;
        this.groupID = groupID;
        this.orderHistory = new OrderHistory(groupID);
    }

    /**
     * First updates the products from the database.
     * 
     * @return the set of products in the usergroup
     */
    public Set<Product> getProducts() {
        List<String> productsFromDB = dbi.getProductsInCommittee(groupID);
        for (String productID : productsFromDB) {
            
            products.add(new Product(productID, groupID));
        }

        return products;
    }

    /**
     * Creates a new product and adds to the existing set of products.
     * Temp solution until we have a working database interface.
     * 
     * @param name
     * @param cost
     * @throws RequestException
     */
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