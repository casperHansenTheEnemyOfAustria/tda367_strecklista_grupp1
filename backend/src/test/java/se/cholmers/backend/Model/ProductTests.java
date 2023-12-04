package se.cholmers.backend.Model;

import org.junit.jupiter.api.Test;

import se.cholmers.backend.Model.Interfaces.IProduct;
import se.cholmers.backend.RequestException;

public class ProductTests {

    @Test
    void ableToCreateProduct() throws RequestException {
        IProduct tProduct = new Product("Test", 0f, "test");
    }

    @Test
    void ableToCreateProductWithNullName() throws RequestException {
        IProduct tProduct = new Product(null, 0f, "test");
    }

    @Test
    void ableToCreateProductWithNullPrice() throws RequestException {
        IProduct tProduct = new Product("Test", null, "test");
    }

    @Test
    void ableToCreateProductWithNullCategory() throws RequestException {
        IProduct tProduct = new Product("Test", 0f, null);
    }

    @Test
    void ableToCreateProductWithNullNameAndPrice() throws RequestException {
        IProduct tProduct = new Product(null, null, "test");
    }

    @Test
    void ableToCreateProductWithNullNameAndCategory() throws RequestException {
        IProduct tProduct = new Product(null, 0f, null);
    }

    @Test
    void ableToCreateProductWithNullPriceAndCategory() throws RequestException {
        IProduct tProduct = new Product("Test", null, null);
    }

    @Test
    void ableToCreateProductWithNullNamePriceAndCategory() throws RequestException {
        IProduct tProduct = new Product(null, null, null);
    }

    @Test
    void ableToCreateProductWithEmptyName() throws RequestException {
        IProduct tProduct = new Product("", 0f, "test");
    }

    @Test
    void ableToCreateProductWithEmptyCategory() throws RequestException {
        IProduct tProduct = new Product("Test", 0f, "");
    }

    @Test
    void ableToCreateProductWithEmptyNameAndCategory() throws RequestException {
        IProduct tProduct = new Product("", 0f, "");
    }

    IProduct createProduct(){
        try {
            IProduct BIGTESTPRODUCT = new Product("Test", 0f, "test");
             return BIGTESTPRODUCT;
        } catch (RequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
}
