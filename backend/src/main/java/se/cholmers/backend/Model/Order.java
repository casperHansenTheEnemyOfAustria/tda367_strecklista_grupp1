package se.cholmers.backend.Model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private LocalDateTime timeStamp;
    private List<Product> products;

    /**
     * Takes a list of products and creates an order based with the current time as
     * a timestamp.
     * 
     * @param products
     */
    public Order(List<Product> products) {
        this.products = products;
        this.timeStamp = LocalDateTime.now();
    }

    public String getTimeString() {
        return timeStamp.toString();
    }

    public List<Product> getProducts() {
        return products;
    }
}