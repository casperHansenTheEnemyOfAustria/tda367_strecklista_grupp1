package se.cholmers.backend.Model.Interfaces;

import se.cholmers.backend.Model.Order;

import java.util.List;

public interface IOrderHistory {
    /**
     * Appends a order to the history.
     *
     * @param order
     */
    void addOrderToHistory(Order order);

    /**
     * @return a list of orders.
     */
    List<Order> getOrderHistory();

    List<String> toStringList();
}
