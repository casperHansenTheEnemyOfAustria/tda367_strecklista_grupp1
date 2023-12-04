package se.cholmers.backend.Model;

import java.util.HashMap;
import java.util.Map;

import se.cholmers.backend.RequestException;

class ProgramState implements se.cholmers.backend.Model.Interfaces.IProgramState {
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

    @Override
    public String getSaldo(String groupID) {
        return currentUser.getSaldo(groupID).toString();
    }

    @Override
    public void addToCart(String productID) {
        Product product = currentUser.getProduct(productID);
        System.out.println(product.getName());
        cart.addToCart(product);
    }

    @Override
    public void removeFromCart(String productID) {
        Product product = currentUser.getProduct(productID);
        cart.removeFromCart(product);
    }

    @Override
    public Map<String, String> getCart() {
        return cart.toStringMap();
    }

    @Override
    public void completePurchase() throws RequestException {
        //TODO: Add logic for changing saldo
        for (Product product : cart.getCart().keySet()) {
            currentUser.purchaseItem(product, cart.getCart().get(product));
        }
        cart.empty();
        return;
    }
}