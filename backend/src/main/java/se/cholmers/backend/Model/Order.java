package se.cholmers.backend.Model;

import se.cholmers.backend.Model.Interfaces.IProduct;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private LocalDateTime timeStamp;
    private List<IProduct> products;

    /**
     * Takes a list of products and creates an order based with the current time as
     * a timestamp.
     * 
     * @param products
     */
    public Order(List<IProduct> products) {
        this.products = products;
        this.timeStamp = LocalDateTime.now();
    }

    public String getTimeString() {
        return timeStamp.toString();
    }

    public List<IProduct> getProducts() {
        return products;
    }
}