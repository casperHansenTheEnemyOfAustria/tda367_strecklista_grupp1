package se.cholmers.backend.Model;

import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import se.cholmers.backend.Model.Interfaces.IOrderHistory;
import se.cholmers.backend.RequestException;
import se.cholmers.backend.newDatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;

class UserGroup implements se.cholmers.backend.Model.Interfaces.IUserGroup {
    private IDatabaseInterface dbi = newDatabaseInterface.getInstance();
    private Set<Product> products = new HashSet<>();
    private Year year;
    private String name;
    private String groupID;
    private IOrderHistory IOrderHistory;

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
        this.IOrderHistory = new OrderHistory();

        //TODO: Remove and handle in DB
        this.groupID = UUID.randomUUID().toString();
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
        this.IOrderHistory = new OrderHistory();
    }

    @Override
    public Set<Product> getProducts() {
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
    @Override
    public void addOrderToHistory(Order order) {
        IOrderHistory.addOrderToHistory(order);
    }

    @Override
    public List<Order> getOrderHistory() {
        return IOrderHistory.getOrderHistory();
    }

    @Override
    public String getID() {
        return this.groupID;
    }
}