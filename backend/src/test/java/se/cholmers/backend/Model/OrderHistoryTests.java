package se.cholmers.backend.Model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import se.cholmers.backend.Model.Interfaces.IOrderHistory;
import se.cholmers.backend.Model.Interfaces.IProduct;
import se.cholmers.backend.RequestException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderHistoryTests {
    IOrderHistory tIOrderHistory;

    @BeforeAll
    void setup() {
        tIOrderHistory = new OrderHistory();
    }

    @Test
    void ableToAddOrdersToHistory() {
        IProduct tProduct;
        try {
            tProduct = new Product("Test", 0f, "test");
              List<IProduct> tProductList = new ArrayList<>();
        tProductList.add(tProduct);
        Order tOrder = new Order(tProductList);
        tIOrderHistory.addOrderToHistory(tOrder);
        } catch (RequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
    }

    @Test
    void ableToAddOrdersToHistoryWithNullProducts() {
        Order tOrder = new Order(null);
        tIOrderHistory.addOrderToHistory(tOrder);
    }

    @Test
    void ableToGetOrderHistoryWhereOrdersAreNull() {
        tIOrderHistory.getOrderHistory();
    }
}
