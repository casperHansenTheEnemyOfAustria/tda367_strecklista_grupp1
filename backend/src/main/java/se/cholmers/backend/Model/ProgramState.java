package se.cholmers.backend.Model;

import java.util.HashMap;
import java.util.Map;

class ProgramState {
    private User currentUser;
    private Cart cart;

    /**
     * Creates a program state given a certain User.
     * 
     * @param user
     */
    public ProgramState(User user) {
        this.currentUser = user;
        this.cart = new Cart();
    }

    /**
     * Returns the saldo as a string for use in the frontend given a groupID for the
     * user owning the ProgramState.
     * 
     * @param groupID
     * @return A string of the saldo a user has in a given group.
     */
    public String getSaldo(String groupID) {
        return currentUser.getSaldo(groupID).toString();
    }

    /**
     * Adds a product to the cart owned by the user owning the ProgramState given
     * the productID as a string.
     * 
     * @param productID
     */
    public void addToCart(String productID) {
        Product product = currentUser.getProduct(productID);
        cart.addToCart(product);
    }

    /**
     * Removes a product from the cart owned by the user owning the ProgramState
     * given the productID as a string.
     * 
     * @param productID
     */
    public void removeFromCart(String productID) {
        Product product = currentUser.getProduct(productID);
        cart.removeFromCart(product);
    }

    /**
     * Returns the contents of the Cart.
     * 
     * @return the contents of the Cart.
     */
    public HashMap<String, String> getCart() {
        return cart.toStringMap();
    }

    /**
     * Empties the cart and updates the saldo (saldo update not yet working)
     */
    public void completePurchase() {
        for (Product product : cart.getCart().keySet()) {
            currentUser.purchaseItem(product, cart.getCart().get(product));
        }
        cart.empty();
        return;
    }
}