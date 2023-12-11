package se.cholmers.backend.Model;

import se.cholmers.backend.Model.Interfaces.IProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import se.cholmers.backend.RequestException;
import se.cholmers.backend.newDatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;

public class Order {
    private LocalDateTime timeStamp;
    private List<IProduct> products;
    private IDatabaseInterface dbi = newDatabaseInterface.getInstance();

    //for testing
    public Order(List<IProduct> products){
        this.products = products;
        this.timeStamp = LocalDateTime.now();
    }
    /**
     * Takes a list of products and creates an order based with the current time as
     * a timestamp. This is for creating a new order.
     * 
     * @param products
     * @param userId
     * @throws RequestException
     */
    public Order(List<IProduct> products, String groupId, String userId) throws RequestException {
        new Order(groupId, userId, LocalDateTime.now(), products);
    }

    /**
     * Takes a list of productIDs and creates an order based with the current time  
     * as a timestamp. This is for getting an existing order from the database.
     * @param userId
     * @param timeStamp
     */
    public Order(String groupId, String userId, LocalDateTime timeStamp) {

    this.timeStamp = timeStamp;
    List<String> productIds = dbi.getOrder(groupId, userId, timeStamp);
    for (String id: productIds) {
        products.add(new Product(id, userId));
    }
    }


    /**
     * Takes a list of productIDs and creates an order based with the current time  
     * as a timestamp. This is for creating a new order.
     * @param userId
     * @param timeStamp
     * @throws RequestException
     */
    public Order(String groupId, String userId, List<String> productIDs, LocalDateTime timeStamp) throws RequestException {
        this.timeStamp = timeStamp;
        for (String id: productIDs) {
            products.add(new Product(id, userId));
        }
        new Order(groupId, userId, timeStamp, products);
    }

    /**
     * Takes a list of products and creates an order based with the current time as
     * a timestamp. This is for creating a new order.
     * 
     * @param products
     * @param userId
     * @param timeStamp
     * @throws RequestException
     */
    public Order(String groupID, String userId, LocalDateTime timeStamp, List<IProduct> products) throws RequestException {
        this.products = products;
        this.timeStamp = timeStamp;
        List<String> productIDs = new ArrayList<>();
        for (IProduct product : products) {
            productIDs.add(product.getID());
        }
        try {
            dbi.addOrder(groupID, userId, timeStamp, productIDs);
        } catch (NullPointerException e) {
            throw new RequestException("could not add order to database due to" + e.getMessage());
        }
        
    }

    

    public String getTimeString() {
        return timeStamp.toString();
    }

    public List<IProduct> getProducts() {
        return products;
    }
}