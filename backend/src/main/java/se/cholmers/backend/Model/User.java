package se.cholmers.backend.Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a user in the system. It contains information about the
 * user such as their name, nickname and phone number
 */
class User {
    private String name;
    private String nick;
    private String phoneNumber;
    private Map<String, Float> saldo = new HashMap<>();
    private Set<UserGroup> groups = new HashSet<>();

    /**
     * This is the constructor for when recreating users from the database
     * 
     * @param userID
     */
    public User(String userID) {

    }

    /**
     * This is the constructor
     * it should be called when a new user is created and also add the
     * user's group to it read from the database
     * 
     * @param name
     * @param nick
     * 
     * @throws NullPointerException if the input is null
     */
    public User(String name, String nick) {
        if (name == null || nick == null) {
            throw new NullPointerException();
        }
        this.name = name;
        this.nick = nick;
    }

    public void addUserToGroup(UserGroup group) {
        groups.add(group);
    }

    /**
     * 
     * @param groupID
     * @return the saldo of the user in the context of its groupID
     * 
     * @throws IllegalArgumentException if the user is not a member of the group
     * @throws NullPointerException     if the user is not a member of any group
     */
    public Float getSaldo(String groupID) {
        for (UserGroup userGroup : groups) {
            if (userGroup.getID() == groupID) {
                return saldo.get(groupID);
            }
        }
        return 0f;
    }

    /**
     * Updates the user saldo based on the price and number of the products it wants
     * to purchase.
     * Negative saldo is permitted.
     * 
     * @param product
     * @param numberOfProducts
     */
    public void purchaseItem(Product product, Integer numberOfProducts) {
        Float currentSaldo = saldo.get(product.getGroupID());
        currentSaldo -= product.getCost() * numberOfProducts;
        saldo.put(product.getGroupID(), currentSaldo);
    }

    /**
     * 
     * @return returns a set of all products the user has access to in all its
     *         usergorups
     * @throws NullPointerException if the user is not a member of any group
     */
    public Set<Product> getAllProducts() {
        Set<Product> allProducts = new HashSet<>();
        for (UserGroup userGroup : groups) {
            allProducts.addAll(userGroup.getProducts());
        }
        return allProducts;
    }

    /**
     * 
     * @param productID
     * @return the product with the given productID logged in its usergroup's
     *         database
     * @throws NullPointerException if the user is not a member of any group
     */
    public Product getProduct(String productID) {
        for (Product product : getAllProducts()) {
            if (product.getID() == productID) {
                return product;
            }
        }
        return null;
    }
}