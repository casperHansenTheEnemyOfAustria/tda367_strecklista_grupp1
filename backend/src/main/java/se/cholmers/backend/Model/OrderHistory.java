package se.cholmers.backend.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import se.cholmers.backend.RequestException;
import se.cholmers.backend.newDatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;

class OrderHistory {
    private List<Order> orders;
    private String groupID;

    IDatabaseInterface dbi = newDatabaseInterface.getInstance();


    /**
     * Constructor for creating a new orderhistory should never be used outside of
     * the UserGroup constructor.
     * @throws RequestException
     */
    OrderHistory(String groupID, String userId) throws RequestException {
        this.groupID = groupID;
        getOrderHistory(userId);
    }

    public OrderHistory(String groupID) {
        this.groupID = groupID;
        this.orders = new ArrayList<>();
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
     * @throws RequestException
     */
    List<Order> getOrderHistory(String userId) throws RequestException {
        List<Map<LocalDateTime, String>> orderHistoryFromDB = dbi.getAllOrders(groupID, userId);
        for (Map<LocalDateTime, String> order : orderHistoryFromDB) {
            for(LocalDateTime time : order.keySet())
                addOrderToHistory(new Order(groupID, order.get(time), dbi.getOrder(groupID, userId, time), time));
        }
        return orders;
    }


        List<Order> getOrderHistory() throws RequestException {
        List<Map<LocalDateTime, String>> orderHistoryFromDB = dbi.getAllOrders(groupID);
        for (Map<LocalDateTime, String> order : orderHistoryFromDB) {
            for(LocalDateTime time : order.keySet())
                for(String userId : dbi.getOrder(groupID,time).keySet())
                    addOrderToHistory(new Order(groupID, userId, dbi.getOrder(groupID, userId, time), time));        }
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