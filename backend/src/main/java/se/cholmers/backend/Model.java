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

        public User(String userID) {

        }

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

        public Set<Product> getAllProducts() {
            Set<Product> allProducts = new HashSet<>();
            for (UserGroup userGroup : groups) {
                allProducts.addAll(userGroup.getProducts());
            }
            return allProducts;
        }

        public Product getProduct(String productID) {
            for (Product product : getAllProducts()) {
                if (product.getID() == productID) {
                    return product;
                }
            }
            return null;
        }
    }

    public class UserGroup {
        Set<Product> products = new HashSet<>();
        Year year;
        String name;
        private OrderHistory orderHistory;

        public Set<Product> getProducts() {
            return products;
        }
    }

    private class Product {
        String name;
        String productID;
        Float cost;

        public String getID() {
            return productID;
        }
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

    public class Cart {
        private Map<Product, Integer> itemsInCart;

        public Cart() {
            itemsInCart = new HashMap<>();
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

        public Map<Product, Integer> getCart() {
            return itemsInCart;
        }

        public void removeFromCart(Product product) {
            Integer currentInCart;
            try {
                currentInCart = itemsInCart.get(product);
            } catch (NullPointerException e) {
                return;
            }
            if (currentInCart > 0) {
                itemsInCart.put(product, --currentInCart);
            }
        }

        public void empty() {
            this.itemsInCart = new HashMap<>();
        }
    }

    private class ProgramState {
        User currentUser;
        Cart cart;

        public ProgramState(User user) {
            this.currentUser = user;
        }

        public String getSaldo(String groupID) {
            return currentUser.getSaldo(groupID).toString();
        }

        public void addToCart(String productID) {
            Product product = currentUser.getProduct(productID);
            cart.addToCart(product);
        }

        public void removeFromCart(String productID) {
            Product product = currentUser.getProduct(productID);
            cart.removeFromCart(product);
        }

        public Cart getCart() {
            return cart;
        }

        public void completePurchase() {
            cart.empty();
            return;
        }
    }
}
