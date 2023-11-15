package se.cholmers.backend;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Model {
    /**
     * This class represents a user in the system. It contains information about the
     * user such as their name, nickname and phone number
     */
    private class User {
        private String name;
        private String nick;
        private String phoneNumber;
        private Map<UserGroup, Float> saldo = new HashMap<>();
        private Set<UserGroup> groups = new HashSet<>();

        public User(String userID) {

        }

        /**
         * 
         * @param name
         * @param nick
         *             this is the constructor
         *             it should be called when a new user is created and also add the
         *             user's group to it read from the database
         */
        public User(String name, String nick) {
            this.name = name;
            this.nick = nick;

            // logic to add the user to the group
        }

        public void addUserToGroup(UserGroup group) {
            groups.add(group);
        }

        /**
         * 
         * @param groupID
         * @return the saldo of the user in the context of its groupID
         * 
         * @throws IllegalArgumentException if the user is not a member of the group
         * @throws NullPointerException     if the user is not a member of any group
         */
        public Float getSaldo(String groupID) {
            for (UserGroup userGroup : groups) {
                if (userGroup.name == groupID) {
                    return saldo.get(userGroup);
                }
            }
            return 0f;
        }

        /**
         * 
         * @return returns a set of all products the user has access to in all its
         *         usergorups
         * @throws NullPointerException if the user is not a member of any group
         */
        public Set<Product> getAllProducts() {
            Set<Product> allProducts = new HashSet<>();
            for (UserGroup userGroup : groups) {
                allProducts.addAll(userGroup.getProducts());
            }
            return allProducts;
        }

        /**
         * 
         * @param productID
         * @return the product with the given productID logged in its usergroup's
         *         database
         * @throws NullPointerException if the user is not a member of any group
         */
        public Product getProduct(String productID) {
            for (Product product : getAllProducts()) {
                if (product.getID() == productID) {
                    return product;
                }
            }
            return null;
        }
    }

    /**
     * This is a group to which users can be assigned.
     * Different groups have different sets of products, names, orderHistories and
     * the user's saldo is different,
     */
    public class UserGroup {
        private Set<Product> products = new HashSet<>();
        private Year year;
        private String name;
        private OrderHistory orderHistory;

        /**
         * 
         * @param name
         * @param year
         * 
         *             this is the constructor for the user group it should add all the
         *             associated products from the database when created
         */
        public UserGroup(String name, Year year) {
            this.name = name;
            this.year = year;
            // code that initializes the object fdrom the database
        }

        /**
         * 
         * @return the set of products in the usergroup up to date with the database
         */
        public Set<Product> getProducts() {
            return products;
        }
    }

    private class Product {
        private String name;
        private String productID;
        private Float cost;

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
        private LocalDateTime timeStamp;
        private List<Product> products;

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
        private User currentUser;
        private Cart cart;

        public ProgramState(User user) {
            this.currentUser = user;
            this.cart = new Cart();
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

    public class StateManager {
        private Map<String, ProgramState> states = new HashMap<>(); // stateID --> state

        public StateManager() {
        }

        public String[] login(String userName, String password) {
            User user = new User(userName, password);
            ProgramState state = new ProgramState(user);

            String stateID = UUID.randomUUID().toString();
            states.put(stateID, state);
            String[] output = { stateID, getAuth(stateID) };
            return output;
        }

        public String logout(String stateID) {
            states.remove(stateID);
            return "log out successful";

        }

        public String getSaldo(String stateID, String groupID) {
            return states.get(stateID).getSaldo(groupID);
        }

        public void addToCart(String stateID, String productID) {
            states.get(stateID).addToCart(productID);
        }

        public void removeFromCart(String stateID, String productID) {
            states.get(stateID).removeFromCart(productID);
        }

        public Cart getCart(String stateID) {
            return states.get(stateID).getCart();
        }

        public void completePurchase(String stateID) {
            states.get(stateID).completePurchase();
        }

        private String getAuth(String stateID) {
            return "test";
        }
    }
}
