package se.cholmers.backend.Model;

import java.util.Map;
import java.util.HashMap;

import se.cholmers.backend.Model.Interfaces.ICart;
import se.cholmers.backend.Model.Interfaces.IProduct;
import se.cholmers.backend.Model.Interfaces.IUser;
import se.cholmers.backend.RequestException;
import se.cholmers.backend.Model.Interfaces.IProgramState;

class ProgramState implements IProgramState {
    private IUser currentUser;
    private ICart cart;

    /**
     * Creates a program state given a certain User.
     * 
     * @param user
     */
    public ProgramState(IUser user) {
        this.currentUser = user;
        this.cart = new Cart();
    }

    @Override
    public String getSaldo(String groupID) {
        return currentUser.getSaldo(groupID).toString();
    }

    @Override
    public void addToCart(String productID) {
        IProduct product = currentUser.getProduct(productID);
        System.out.println(product.getName());
        cart.addToCart(product);
    }

    @Override
    public void removeFromCart(String productID) {
        IProduct product = currentUser.getProduct(productID);
        cart.removeFromCart(product);
    }

    @Override
    public Map<String, String> getCart() {
        return cart.toStringMap();
    }

    @Override
    public void completePurchase() throws RequestException {
        //TODO: Add logic for changing saldo
        for (IProduct product : cart.getCart().keySet()) {
            currentUser.purchaseItem(product, cart.getCart().get(product));
        }
        cart.empty();
        return;
    }

    @Override
    public Map<String, String> getProducts() {
        Map<String, String> output = new HashMap<String, String>();
        for (IProduct p : currentUser.getAllProducts()) {
            output.put(p.getName(), p.getPrice().toString());
        }
        return output;
    }
}