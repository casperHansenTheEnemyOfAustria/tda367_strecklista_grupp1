package se.cholmers.backend.Model.Interfaces;

import se.cholmers.backend.RequestException;

import java.util.Map;

public interface IProgramState {
    /**
     * Returns the saldo as a string for use in the frontend given a groupID for the
     * user owning the ProgramState.
     *
     * @param groupID The groupID of the group the user is in.
     * @return A string of the saldo a user has in a given group.
     */
    String getSaldo(String groupID);

    /**
     * Adds a product to the cart owned by the user owning the ProgramState given
     * the productID as a string.
     *
     * @param productID The productID of the product to be added to the cart.
     */
    void addToCart(String productID);

    /**
     * Removes a product from the cart owned by the user owning the ProgramState
     * given the productID as a string.
     *
     * @param productID The productID of the product to be removed from the cart.
     */
    void removeFromCart(String productID);

    /**
     * Returns the contents of the Cart.
     *
     * @return the contents of the Cart.
     */
    Map<String, String> getCart();

    /**
     * Empties the cart and updates the saldo (saldo update not yet working)
     *
     * @throws RequestException if the request fails.
     */
    void completePurchase() throws RequestException;
}
