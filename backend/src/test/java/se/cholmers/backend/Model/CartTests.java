package se.cholmers.backend.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartTests {
    Cart cart;

    @BeforeEach
    void setup() {
        cart = new Cart();
    }

    @Test
    Cart addProductToCartWorks() {
        Product myProduct = new Product("testName", 0f, "testGroupID");
        cart.addToCart(myProduct);
        assertEquals(cart.getCart().get(myProduct), 1);
        cart.addToCart(myProduct);
        assertEquals(cart.getCart().get(myProduct), 2);
        cart.addToCart(null);
        Product nullProduct = new Product(null, null, null);
        cart.addToCart(nullProduct);

        return cart;
    }

    @Test
    void getCartWithNulls() {
        cart = addProductToCartWorks();
        cart.toStringMap();
    }

    @Test
    void getCartWithNullsAndEmpty() {
        cart = addProductToCartWorks();
        cart.toStringMap();
        cart.getCart().clear();
        cart.toStringMap();
    }

    @Test
    void removeProductFromCart() {
        Product myProduct = new Product("testName", 0f, "testGroupID");
        cart.addToCart(myProduct);
        cart.addToCart(myProduct);
        cart.removeFromCart(myProduct);
        assertEquals(cart.getCart().get(myProduct), 1);
        cart.removeFromCart(myProduct);
        assertEquals(cart.getCart().get(myProduct), 0);
        cart.removeFromCart(myProduct);
        assertEquals(cart.getCart().get(myProduct), 0);
    }
}
