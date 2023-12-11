package se.cholmers.backend.Model.Interfaces;

import se.cholmers.backend.RequestException;
import se.cholmers.backend.Model.Order;

import java.util.List;

public interface IOrderHistory {
    /**
     * Appends a order to the history.
     *
     * @param order
     */
    void addOrderToHistory(Order order);

    List<Order> getOrderHistory() throws RequestException;

    /**
     * @return a list of orders.
     * @throws RequestException
     */

    List<String> toStringList();

    List<IOrder> getOrderHistory(String user) throws RequestException;
}
