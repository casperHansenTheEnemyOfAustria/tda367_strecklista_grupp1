package se.cholmers.backend.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import se.cholmers.backend.Model.Interfaces.IOrder;
import se.cholmers.backend.RequestException;
import se.cholmers.backend.newDatabaseInterface;
// import se.cholmers.backend.DatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;
import se.cholmers.backend.Model.Interfaces.IOrderHistory;
import se.cholmers.backend.Model.Interfaces.IProduct;

class OrderHistory implements IOrderHistory {
    private List<IOrder> orders;
    private String groupID;

    private IDatabaseInterface dbi = newDatabaseInterface.getInstance();

    /**
     * Constructor for creating a new orderhistory should never be used outside of
     * the UserGroup constructor.
     * 
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
    @Override
    public void addOrderToHistory(IOrder order) {
        orders.add(order);
    }

    /**
     * @return
     * @throws RequestException
     */
    @Override
    public List<IOrder> getOrderHistory(String userId) throws RequestException {
        List<Map<LocalDateTime, String>> orderHistoryFromDB = dbi.getAllOrders(groupID, userId);
        orders = new ArrayList<>();
        LocalDateTime prev = null;
        for (Map<LocalDateTime, String> order : orderHistoryFromDB) {
            
            for (LocalDateTime time : order.keySet()){
                if(prev != null && prev.isEqual(time)) continue;
                addOrderToHistory(new Order(groupID, userId, time));
                prev = time;
            }
        }
        return orders;
    }

    @Override
    public List<IOrder> getOrderHistory() throws RequestException {
        List<Map<LocalDateTime, String>> orderHistoryFromDB = dbi.getAllOrders(groupID);
        orders = new ArrayList<>();
        for (Map<LocalDateTime, String> order : orderHistoryFromDB) {
            for (LocalDateTime time : order.keySet()) {
                for (String userId : dbi.getOrder(groupID, time).keySet()) {
                    addOrderToHistory(new Order(groupID, userId, time));
                }
            }
        }
        return orders;
    }

    @Override
    public List<String> toStringList() {
        List<String> stringList = new ArrayList<>();
        for (IOrder order : orders) {
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