package se.cholmers.backend.Model.Interfaces;

import se.cholmers.backend.Model.Order;
import se.cholmers.backend.Model.Product;
import se.cholmers.backend.RequestException;

import java.util.List;
import java.util.Set;

public interface IUserGroup {
    /**
     * First updates the products from the database.
     *
     * @return the set of products in the usergroup
     */
    Set<Product> getProducts();

    /**
     * Creates a new product and adds to the existing set of products.
     * Temp solution until we have a working database interface.
     *
     * @param name
     * @param cost
     * @throws RequestException
     */
    void addProduct(String name, Float cost) throws RequestException;

    /**
     * Adds an order to the group's orderhistory
     *
     * @param order
     */
    void addOrderToHistory(Order order);

    /**
     * First updates the history from the database.
     *
     * @return the order history for a group
     */
    List<Order> getOrderHistory();

    /**
     * @return the groupID of a certain group.
     */
    String getID();
}
