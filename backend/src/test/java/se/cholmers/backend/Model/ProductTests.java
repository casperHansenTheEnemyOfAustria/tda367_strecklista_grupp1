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
