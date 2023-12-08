package se.cholmers.backend.Model;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> toStringList() {
        List<String> stringList = new ArrayList<>();
        for (Order order : orders) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(order.getTimeString());
            stringBuilder.append(", ");
            for (Product product : order.getProducts()) {
                stringBuilder.append(product.toString());
                stringBuilder.append(", ");
            }
            stringList.add(stringBuilder.toString());
        }
        return stringList;
    }
}