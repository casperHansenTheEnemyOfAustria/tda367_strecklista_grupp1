package se.cholmers.backend.Model.Interfaces;

import se.cholmers.backend.Model.Product;

import java.util.Map;

public interface ICart {
    /**
     * Adds a given product to the cart
     *
     * @throws NullPointerException
     */
    void addToCart(Product product);

    /**
     * Returns a map of all the products and amount of each contained in the cart.
     *
     * @return
     */
    Map<Product, Integer> getCart();

    /**
     * Removes a given product from the cart.
     *
     * @param product
     */
    void removeFromCart(Product product);

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
