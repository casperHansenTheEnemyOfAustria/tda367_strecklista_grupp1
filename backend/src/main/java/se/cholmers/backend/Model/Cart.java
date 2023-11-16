package se.cholmers.backend.Model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> itemsInCart;

    /**
     * Constructor for Cart, initializes the internal Map.
     */
    public Cart() {
        itemsInCart = new HashMap<>();
    }

    /**
     * Adds a given product to the cart
     */
    public void addToCart(Product product) {
        Integer currentInCart;
        try {
            currentInCart = itemsInCart.get(product);
        } catch (NullPointerException e) {
            currentInCart = 0;
        }
        itemsInCart.put(product, ++currentInCart);
    }

    /**
     * Returns a map of all the products and amount of each contained in the cart.
     * 
     * @return
     */
    public Map<Product, Integer> getCart() {
        return itemsInCart;
    }

    /**
     * Removes a given product from the cart.
     * 
     * @param product
     */
    public void removeFromCart(Product product) {
        Integer currentInCart;
        try {
            currentInCart = itemsInCart.get(product);
        } catch (NullPointerException e) {
            return;
        }
        if (currentInCart > 0) {
            itemsInCart.put(product, --currentInCart);
        }
    }

    /**
     * Clears the cart from all items.
     */
    public void empty() {
        this.itemsInCart = new HashMap<>();
    }
}