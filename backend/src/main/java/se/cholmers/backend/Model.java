package se.cholmers.backend;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Model {
    private class User {
        String name;
        String nick;
        String phoneNumber;
        Map<UserGroup, Float> saldo = new HashMap<>();
        private Set<UserGroup> groups = new HashSet<>();

        public User(String name, String nick) {
            this.name = name;
            this.nick = nick;
        }

        public void addUserToGroup(UserGroup group) {
            groups.add(group);
        }

        public Float getSaldo(String groupID) {
            for (UserGroup userGroup : groups) {
                if (userGroup.name == groupID) {
                    return saldo.get(userGroup);
                }
            }
            return 0f;
        }
    }

    private class UserGroup {
        List<Product> products = new ArrayList<>();
        Year year;
        String name;
        private OrderHistory orderHistory;
    }

    private class Product {
        String name;
        Float cost;
    }

    private class OrderHistory {
        List<Order> orders = new ArrayList<>();

        public OrderHistory() {
        }

        public void addOrderToHistory(Order order) {
            orders.add(order);
        }

        public List<Order> getOrderHistory() {
            return orders;
        }
    }

    private class Order {
        LocalDateTime timeStamp;
        List<Product> products;

        /**
         * Takes a list of products and creates an order based with the current time as
         * a timestamp.
         * 
         * @param products
         */
        public Order(List<Product> products) {
            this.products = products;
            this.timeStamp = LocalDateTime.now();
        }
    }

    private class Cart {
        Map<Product, Integer> itemsInCart;

        public Cart() {
        }

        public void addToCart(Product product) {
            Integer currentInCart;
            try {
                currentInCart = itemsInCart.get(product);
            } catch (NullPointerException e) {
                currentInCart = 0;
            }
            itemsInCart.put(product, ++currentInCart);
        }
    }

    private class ProgramState {
        User currentUser;

        public ProgramState(User user) {
            this.currentUser = user;
        }

        public String getSaldo(String groupID) {
            return currentUser.getSaldo(groupID).toString();
        }

    }
}
