package se.cholmers.backend.Model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import se.cholmers.backend.Model.Interfaces.IProduct;
import se.cholmers.backend.RequestException;

class OrderTests {

    @Test
    void ableToCreateOrder() {
        IProduct tProduct;
        try {
            tProduct = new Product("Test", 0f, "test");
                List<IProduct> tProductList = new ArrayList<>();
        tProductList.add(tProduct);
        Order tOrder = new Order(tProductList);
        } catch (RequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
    }

    @Test
    void ableToCreateOrderWithNullProducts() {
        Order tOrder = new Order(null);
    }
}
