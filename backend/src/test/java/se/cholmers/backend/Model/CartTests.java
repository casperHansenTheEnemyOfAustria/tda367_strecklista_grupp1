package se.cholmers.backend.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartTests {

    Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void addProductToCartWorks() {
        Product myProduct = new Product("testName", 0f, "testGroupID");
        cart.addToCart(myProduct);
        assertEquals(cart.getCart().get(myProduct), 1);
        cart.addToCart(myProduct);
        assertEquals(cart.getCart().get(myProduct), 2);
        cart.addToCart(null);
    }

    @Test
    void cartIsEmptiedOnCheckout() {

    }

}
