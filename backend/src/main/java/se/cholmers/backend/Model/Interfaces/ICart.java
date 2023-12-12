package se.cholmers.backend.Model.Interfaces;

import java.util.Map;

public interface ICart {
    /**
     * Adds a given product to the cart
     *
     * @throws NullPointerException
     */
    void addToCart(IProduct product);

    /**
     * Returns a map of all the products and amount of each contained in the cart.
     *
     * @return
     */
    Map<IProduct, Integer> getCart();

    /**
     * Removes a given product from the cart.
     *
     * @param product
     */
    void removeFromCart(IProduct product);

    /**
     * Clears the cart from all items.
     */
    void empty();

    /**
     * Returns a map of products ans their amount in the cart as a string map.
     *
     * @return map of product name and amount
     */
    Map<String, String> toStringMap();
}
