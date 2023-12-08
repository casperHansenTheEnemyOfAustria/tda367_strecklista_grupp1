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
     * @param userId
     * @throws RequestException
     */
    public Order(List<Product> products, String userId) throws RequestException {
        new Order(userId, LocalDateTime.now(), products);
    }

    /**
     * Takes a list of productIDs and creates an order based with the current time  
     * as a timestamp. This is for getting an existing order from the database.
     * @param userId
     * @param timeStamp
     */
    public Order(String userId, LocalDateTime timeStamp) {

    this.timeStamp = timeStamp;
    List<String> productIds = dbi.getOrder(userId, timeStamp);
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
    public Order(String userId, List<String> productIDs, LocalDateTime timeStamp) throws RequestException {
        this.timeStamp = timeStamp;
        for (String id: productIDs) {
            products.add(new Product(id, userId));
        }
        new Order(userId, timeStamp, products);
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
    public Order(String userId, LocalDateTime timeStamp, List<Product> products) throws RequestException {
        this.products = products;
        this.timeStamp = timeStamp;
        List<String> productIDs = new ArrayList<>();
        for (Product product : products) {
            productIDs.add(product.getID());
        }
        try {
            dbi.addOrder(userId, timeStamp, productIDs);
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