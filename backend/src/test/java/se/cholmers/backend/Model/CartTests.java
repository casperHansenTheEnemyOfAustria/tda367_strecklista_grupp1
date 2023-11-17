package se.cholmers.backend.Model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CartTests {
    Cart cart;

    @BeforeAll
    void setup() {
        cart = new Cart();
    }

    @Test
    void addProductToCartWorks() {
        Product myProduct = new Product("testName", 0f, "testGroupID");
        cart.addToCart(myProduct);
        assertEquals(cart.getCart().get(myProduct), 1);
        cart.addToCart(myProduct);
        assertEquals(cart.getCart().get(myProduct), 2);
    }

    @Test
    void addNullsToCart() {
        assertDoesNotThrow(() -> cart.addToCart(null));
        Product nullProduct = new Product(null, null, null);
        assertDoesNotThrow(() -> cart.addToCart(nullProduct));
    }

    @Test
    void getCartWithNulls() {
        addNullsToCart();
        cart.toStringMap();
    }

    @Test
    void getCartWithNullsAndEmpty() {
        addNullsToCart();
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
