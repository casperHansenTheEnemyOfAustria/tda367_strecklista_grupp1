package se.cholmers.backend.Model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class OrderTests {

    @Test
    void ableToCreateOrder() {
        Product tProduct = new Product("Test", 0f, "test");
        List<Product> tProductList = new ArrayList<>();
        tProductList.add(tProduct);
        Order tOrder = new Order(tProductList);
    }

    @Test
    void ableToCreateOrderWithNullProducts() {
        Order tOrder = new Order(null);
    }
}
