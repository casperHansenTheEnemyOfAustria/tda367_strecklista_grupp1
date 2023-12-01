package se.cholmers.backend.Model;

import org.junit.jupiter.api.Test;

import se.cholmers.backend.RequestException;

public class ProductTests {

    @Test
    void ableToCreateProduct() throws RequestException {
        Product tProduct = new Product("Test", 0f, "test");
    }

    @Test
    void ableToCreateProductWithNullName() throws RequestException {
        Product tProduct = new Product(null, 0f, "test");
    }

    @Test
    void ableToCreateProductWithNullPrice() throws RequestException {
        Product tProduct = new Product("Test", null, "test");
    }

    @Test
    void ableToCreateProductWithNullCategory() throws RequestException {
        Product tProduct = new Product("Test", 0f, null);
    }

    @Test
    void ableToCreateProductWithNullNameAndPrice() throws RequestException {
        Product tProduct = new Product(null, null, "test");
    }

    @Test
    void ableToCreateProductWithNullNameAndCategory() throws RequestException {
        Product tProduct = new Product(null, 0f, null);
    }

    @Test
    void ableToCreateProductWithNullPriceAndCategory() throws RequestException {
        Product tProduct = new Product("Test", null, null);
    }

    @Test
    void ableToCreateProductWithNullNamePriceAndCategory() throws RequestException {
        Product tProduct = new Product(null, null, null);
    }

    @Test
    void ableToCreateProductWithEmptyName() throws RequestException {
        Product tProduct = new Product("", 0f, "test");
    }

    @Test
    void ableToCreateProductWithEmptyCategory() throws RequestException {
        Product tProduct = new Product("Test", 0f, "");
    }

    @Test
    void ableToCreateProductWithEmptyNameAndCategory() throws RequestException {
        Product tProduct = new Product("", 0f, "");
    }

    Product createProduct(){
        try {
            Product BIGTESTPRODUCT = new Product("Test", 0f, "test");
             return BIGTESTPRODUCT;
        } catch (RequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
}
