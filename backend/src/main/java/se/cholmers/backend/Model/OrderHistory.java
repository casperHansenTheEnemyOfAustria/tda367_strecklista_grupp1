package se.cholmers.backend.Model;

import se.cholmers.backend.Model.Interfaces.IProduct;

import java.util.ArrayList;
import java.util.List;

class OrderHistory implements se.cholmers.backend.Model.Interfaces.IOrderHistory {
    List<Order> orders;

    /**
     * Constructor for creating a new orderhistory should never be used outside of
     * the UserGroup constructor.
     */
    OrderHistory() {
        orders = new ArrayList<>();
    }

    @Override
    public void addOrderToHistory(Order order) {
        orders.add(order);
    }

    @Override
    public List<Order> getOrderHistory() {
        return orders;
    }

    @Override
    public List<String> toStringList() {
        List<String> stringList = new ArrayList<>();
        for (Order order : orders) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(order.getTimeString());
            stringBuilder.append(", ");
            for (IProduct product : order.getProducts()) {
                stringBuilder.append(product.toString());
                stringBuilder.append(", ");
            }
            stringList.add(stringBuilder.toString());
        }
        return stringList;
    }
}