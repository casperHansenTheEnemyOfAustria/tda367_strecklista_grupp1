package se.cholmers.backend.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import se.cholmers.backend.RequestException;
import se.cholmers.backend.newDatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;

class Order {
    private LocalDateTime timeStamp;
    private List<Product> products;
    private IDatabaseInterface dbi = newDatabaseInterface.getInstance();

    //for testing
    public Order(List<Product> products){
        this.products = products;
        this.timeStamp = LocalDateTime.now();
    }
    /**
     * Takes a list of products and creates an order based with the current time as
     * a timestamp. This is for creating a new order.
     * 
     * @param products
     * @param groupID
     * @throws RequestException
     */
    public Order(List<Product> products, String groupID) throws RequestException {
        new Order(groupID, LocalDateTime.now(), products);
    }

    /**
     * Takes a list of productIDs and creates an order based with the current time  
     * as a timestamp. This is for getting an existing order from the database.
     * @param groupID
     * @param timeStamp
     */
    public Order(String groupID, LocalDateTime timeStamp) {

    this.timeStamp = timeStamp;
    List<String> productIds = dbi.getOrder(groupID, timeStamp);
    for (String id: productIds) {
        products.add(new Product(id, groupID));
    }
    }


    /**
     * Takes a list of productIDs and creates an order based with the current time  
     * as a timestamp. This is for creating a new order.
     * @param groupID
     * @param timeStamp
     * @throws RequestException
     */
    public Order(String groupID, List<String> productIDs, LocalDateTime timeStamp) throws RequestException {
        this.timeStamp = timeStamp;
        for (String id: productIDs) {
            products.add(new Product(id, groupID));
        }
        new Order(groupID, timeStamp, products);
    }

    /**
     * Takes a list of products and creates an order based with the current time as
     * a timestamp. This is for creating a new order.
     * 
     * @param products
     * @param groupID
     * @param timeStamp
     * @throws RequestException
     */
    public Order(String groupID, LocalDateTime timeStamp, List<Product> products) throws RequestException {
        this.products = products;
        this.timeStamp = timeStamp;
        List<String> productIDs = new ArrayList<>();
        for (Product product : products) {
            productIDs.add(product.getID());
        }
        try {
            dbi.addOrder(groupID, timeStamp, productIDs);
        } catch (NullPointerException e) {
            throw new RequestException("could not add order to database due to" + e.getMessage());
        }
        
    }

    

    public String getTimeString() {
        return timeStamp.toString();
    }

    public List<Product> getProducts() {
        return products;
    }
}