package se.cholmers.backend.Model;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class OrderHistoryTests {
    OrderHistory tOrderHistory;

    @BeforeAll
    void setup() {
        tOrderHistory = new OrderHistory();
    }

    @Test
    OrderHistory ableToAddOrdersToHistory() {
        Product tProduct = new Product("Test", 0f, "test");
        List<Product> tProductList = new ArrayList<>();
        tProductList.add(tProduct);
        Order tOrder = new Order(tProductList);
        tOrderHistory.addOrderToHistory(tOrder);
        Order nullOrder = new Order(null);
        Product nullProduct = new Product(null, null, null);
        List<Product> nullList = new ArrayList<>();
        tOrderHistory.addOrderToHistory(nullOrder);
        tOrderHistory.addOrderToHistory(new Order(nullList));
        nullList.add(nullProduct);
        tOrderHistory.addOrderToHistory(new Order(nullList));

        return tOrderHistory;
    }

    @Test
    void displayNullifiedOrderHistory() {
        tOrderHistory = ableToAddOrdersToHistory();
        tOrderHistory.getOrderHistory();
    }
}
