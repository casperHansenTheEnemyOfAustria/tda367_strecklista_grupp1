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
        private Map<String, Float> saldo = new HashMap<>();
        private Set<UserGroup> groups = new HashSet<>();

        /**
         * This is the constructor for when recreating users from the database
         * 
         * @param userID
         */
        public User(String userID) {

        }

        /**
         * This is the constructor
         * it should be called when a new user is created and also add the
         * user's group to it read from the database
         * 
         * @param name
         * @param nick
         * 
         */
        public User(String name, String nick) {
            this.name = name;
            this.nick = nick;
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
                if (userGroup.getID() == groupID) {
                    return saldo.get(groupID);
                }
            }
            return 0f;
        }

        /**
         * Updates the user saldo based on the price and number of the products it wants
         * to purchase.
         * Negative saldo is permitted.
         * 
         * @param product
         * @param numberOfProducts
         */
        public void purchaseItem(Product product, Integer numberOfProducts) {
            Float currentSaldo = saldo.get(product.getGroupID());
            currentSaldo -= product.getCost() * numberOfProducts;
            saldo.put(product.getGroupID(), currentSaldo);
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

    public class UserGroup {
        private Set<Product> products = new HashSet<>();
        private Year year;
        private String name;
        private String groupID;
        private OrderHistory orderHistory;

        public UserGroup(String name, Year year) {
            this.name = name;
            this.year = year;
            this.orderHistory = new OrderHistory();
            this.groupID = UUID.randomUUID().toString();
            // code that initializes the object from the database
        }

        public UserGroup(String groupID) {
            // code that initializes the object from the database
        }

        /**
         * First updates the products from the database.
         * 
         * @return the set of products in the usergroup
         */
        public Set<Product> getProducts() {
            return products;
        }

        /**
         * Creates a new product and adds to the existing set of products.
         * Temp solution until we have a working database interface.
         * 
         * @param name
         * @param cost
         */
        public void addProduct(String name, Float cost) {
            products.add(new Product(name, cost, groupID));
        }

        /**
         * Adds an order to the group's orderhistory
         * 
         * @param order
         */
        public void addOrderToHistory(Order order) {
            orderHistory.addOrderToHistory(order);
        }

        /**
         * First updates the history from the database.
         * 
         * @return the order history for a group
         */
        public List<Order> getOrderHistory() {
            return orderHistory.getOrderHistory();
        }

        public String getID() {
            return this.groupID;
        }
    }

    private class Product {
        private String name;
        private String productID;
        private Float cost;
        private String groupID;

        /**
         * Creates a new product with a random ID given the parameters.
         * 
         * @param name (the name of the product.)
         * @param cost (the price of the product in SEK.)
         */
        public Product(String name, Float cost, String groupID) {
            this.name = name;
            this.cost = cost;
            this.groupID = groupID;
            productID = UUID.randomUUID().toString();
        }

        public String getGroupID() {
            return groupID;
        }

        public Float getCost() {
            return cost;
        }

        /**
         * Gets the ID of a product
         * 
         * @return the ID of the product
         */
        public String getID() {
            return productID;
        }
    }

    private class OrderHistory {
        List<Order> orders;

        /**
         * Constructor for creating a new orderhistory should never be used outside of
         * the UserGroup constructor.
         */
        OrderHistory() {
            orders = new ArrayList<>();
        }

        /**
         * Appends a order to the history.
         * 
         * @param order
         */
        void addOrderToHistory(Order order) {
            orders.add(order);
        }

        /**
         * @return a list of orders.
         */
        List<Order> getOrderHistory() {
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

        /**
         * Constructor for Cart, initializes the internal Map.
         */
        public Cart() {
            itemsInCart = new HashMap<>();
        }

        /**
         * Adds a given product to the cart
         */
        public void addToCart(Product product) {
            Integer currentInCart;
            try {
                currentInCart = itemsInCart.get(product);
            } catch (NullPointerException e) {
                currentInCart = 0;
            }
            itemsInCart.put(product, ++currentInCart);
        }

        /**
         * Returns a map of all the products and amount of each contained in the cart.
         * 
         * @return
         */
        public Map<Product, Integer> getCart() {
            return itemsInCart;
        }

        /**
         * Removes a given product from the cart.
         * 
         * @param product
         */
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

        /**
         * Clears the cart from all items.
         */
        public void empty() {
            this.itemsInCart = new HashMap<>();
        }
    }

    private class ProgramState {
        private User currentUser;
        private Cart cart;

        /**
         * Creates a program state given a certain User.
         * 
         * @param user
         */
        public ProgramState(User user) {
            this.currentUser = user;
            this.cart = new Cart();
        }

        /**
         * Returns the saldo as a string for use in the frontend given a groupID for the
         * user owning the ProgramState.
         * 
         * @param groupID
         * @return A string of the saldo a user has in a given group.
         */
        public String getSaldo(String groupID) {
            return currentUser.getSaldo(groupID).toString();
        }

        /**
         * Adds a product to the cart owned by the user owning the ProgramState given
         * the productID as a string.
         * 
         * @param productID
         */
        public void addToCart(String productID) {
            Product product = currentUser.getProduct(productID);
            cart.addToCart(product);
        }

        /**
         * Removes a product from the cart owned by the user owning the ProgramState
         * given the productID as a string.
         * 
         * @param productID
         */
        public void removeFromCart(String productID) {
            Product product = currentUser.getProduct(productID);
            cart.removeFromCart(product);
        }

        /**
         * Returns the contents of the Cart.
         * 
         * @return the contents of the Cart.
         */
        public Map<Product, Integer> getCart() {
            return cart.getCart();
        }

        /**
         * Empties the cart and updates the saldo (saldo update not yet working)
         */
        public void completePurchase() {
            for (Product product : getCart().keySet()) {
                currentUser.purchaseItem(product, getCart().get(product));
            }
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
