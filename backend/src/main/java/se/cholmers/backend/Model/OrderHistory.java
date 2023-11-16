package se.cholmers.backend.Model;

import java.util.ArrayList;
import java.util.List;

import se.cholmers.backend.Model;
import se.cholmers.backend.Model.Order;

class OrderHistory {
    List<Order> orders;

    /**
     * Constructor for creating a new orderhistory should never be used outside of
     * the UserGroup constructor.
     */
    OrderHistory() {
        orders = new ArrayList<>();
    }

    /**
     * Appends a order to the history.
     * 
     * @param order
     */
    void addOrderToHistory(Order order) {
        orders.add(order);
    }

    /**
     * @return a list of orders.
     */
    List<Order> getOrderHistory() {
        return orders;
    }
}