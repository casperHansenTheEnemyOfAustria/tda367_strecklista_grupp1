package se.cholmers.backend.Model;

import org.junit.jupiter.api.Test;

public class ProductTests {

    @Test
    void ableToCreateProduct() {
        Product tProduct = new Product("Test", 0f, "test");
    }

    @Test
    void ableToCreateProductWithNullName() {
        Product tProduct = new Product(null, 0f, "test");
    }

    @Test
    void ableToCreateProductWithNullPrice() {
        Product tProduct = new Product("Test", null, "test");
    }

    @Test
    void ableToCreateProductWithNullCategory() {
        Product tProduct = new Product("Test", 0f, null);
    }

    @Test
    void ableToCreateProductWithNullNameAndPrice() {
        Product tProduct = new Product(null, null, "test");
    }

    @Test
    void ableToCreateProductWithNullNameAndCategory() {
        Product tProduct = new Product(null, 0f, null);
    }

    @Test
    void ableToCreateProductWithNullPriceAndCategory() {
        Product tProduct = new Product("Test", null, null);
    }

    @Test
    void ableToCreateProductWithNullNamePriceAndCategory() {
        Product tProduct = new Product(null, null, null);
    }

    @Test
    void ableToCreateProductWithEmptyName() {
        Product tProduct = new Product("", 0f, "test");
    }

    @Test
    void ableToCreateProductWithEmptyCategory() {
        Product tProduct = new Product("Test", 0f, "");
    }

    @Test
    void ableToCreateProductWithEmptyNameAndCategory() {
        Product tProduct = new Product("", 0f, "");
    }

}
