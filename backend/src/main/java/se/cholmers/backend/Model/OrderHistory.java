package se.cholmers.backend.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import se.cholmers.backend.newDatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;

class OrderHistory {
    List<Order> orders;
    String groupID;
    IDatabaseInterface dbi = newDatabaseInterface.getInstance();


    /**
     * Constructor for creating a new orderhistory should never be used outside of
     * the UserGroup constructor.
     */
    OrderHistory(String groupID) {
        this.groupID = groupID;
        getOrderHistory();
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
        List<Map<LocalDateTime, List<String>>> orderHistoryFromDB = dbi.getAllOrders(groupID);
        for (Map<LocalDateTime, List<String>> order : orderHistoryFromDB) {
            for(LocalDateTime time : order.keySet())
                addOrderToHistory(new Order(groupID, order.get(time), time));
        }
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