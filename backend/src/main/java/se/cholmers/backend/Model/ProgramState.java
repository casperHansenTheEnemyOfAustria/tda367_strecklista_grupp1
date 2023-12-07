package se.cholmers.backend.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.cholmers.backend.RequestException;

class ProgramState {
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
    public Map<String, String> getCart() {
        return cart.toStringMap();
    }

    /**
     * Empties the cart and updates the saldo (saldo update not yet working)
     * @throws RequestException
     */
    public void completePurchase() throws RequestException {
        //TODO: Add logic for changing saldo
        for (Product product : cart.getCart().keySet()) {
            currentUser.purchaseItem(product, cart.getCart().get(product));
        }
        cart.empty();
        return;
    }

    /**
     * returns a list of product maps
     * @return
     */
    public List<Map<String, String>> getAllProducts() {
        List<Map<String, String>> allProducts = new ArrayList<>();
        for (Product p : currentUser.getAllProducts()) {
            allProducts.add(getProduct(p.getID()));
        }
        return allProducts;
    }

    /**
     * returns a map of the product info with the structure
     * Name
     * Price
     * Amount
     * id
     * @param productID
     * @return
     */
    public Map<String, String> getProduct(String productID){
        Product tempProd = currentUser.getProduct(productID);
        Map<String, String> output = new HashMap<>();
        output.put("Name", tempProd.getName());
        output.put("Price", tempProd.getCost().toString());
        output.put("Amount", tempProd.getAmount());
        output.put("Id", productID);
        return output;

    }

    public void increaseProductAmount(String productID, String amount) throws NumberFormatException, RequestException{
        Product tempProd = currentUser.getProduct(productID);
        tempProd.increaseAmount(Integer.parseInt(amount));
    }
}