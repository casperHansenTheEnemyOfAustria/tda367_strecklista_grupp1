package se.cholmers.backend.Model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import se.cholmers.backend.RequestException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderHistoryTests {
    OrderHistory tOrderHistory;

    @BeforeAll
    void setup() {
        try {
            tOrderHistory = new OrderHistory("test");
        } catch (RequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void ableToAddOrdersToHistory() {
        Product tProduct;
        try {
            tProduct = new Product("Test", 0f, "test");
              List<Product> tProductList = new ArrayList<>();
        tProductList.add(tProduct);
        Order tOrder = new Order(tProductList);
        tOrderHistory.addOrderToHistory(tOrder);
        } catch (RequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
    }

    @Test
    void ableToAddOrdersToHistoryWithNullProducts() {
        Order tOrder = new Order(null);
        tOrderHistory.addOrderToHistory(tOrder);
    }

    @Test
    void ableToGetOrderHistoryWhereOrdersAreNull() {
        try {
            tOrderHistory.getOrderHistory();
        } catch (RequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
