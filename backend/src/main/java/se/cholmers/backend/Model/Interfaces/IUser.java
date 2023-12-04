package se.cholmers.backend.Model.Interfaces;

import se.cholmers.backend.Model.Product;
import se.cholmers.backend.Model.UserGroup;
import se.cholmers.backend.RequestException;

import java.util.Set;

public interface IUser {
    void addUserToGroup(UserGroup group);

    /**
     * @param groupID
     * @return the saldo of the user in the context of its groupID
     * @throws IllegalArgumentException if the user is not a member of the group
     * @throws NullPointerException     if the user is not a member of any group
     */
    Float getSaldo(String groupID);

    /**
     * Updates the user saldo based on the price and number of the products it wants
     * to purchase.
     * Negative saldo is permitted.
     *
     * @param product
     * @param numberOfProducts
     * @throws RequestException
     */
    void purchaseItem(Product product, Integer numberOfProducts) throws RequestException;

    /**
     * @return returns a set of all products the user has access to in all its
     * usergorups
     * @throws NullPointerException if the user is not a member of any group
     */
    Set<Product> getAllProducts();

    /**
     * @param productID
     * @return the product with the given productID logged in its usergroup's
     * database
     * @throws NullPointerException if the user is not a member of any group
     */
    Product getProduct(String productID);
}
