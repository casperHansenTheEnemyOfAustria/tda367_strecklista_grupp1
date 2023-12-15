package se.cholmers.backend.Model;

import java.util.*;

import se.cholmers.backend.Model.Interfaces.ICart;
import se.cholmers.backend.Model.Interfaces.IOrder;
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

    /**
     * Adds a product to the cart owned by the user owning the ProgramState given
     * the productID as a string.
     * 
     * @param productID
     * @throws RequestException
     */
    @Override
    public void addToCart(String productID) throws RequestException {
        IProduct product = currentUser.getProduct(productID);
        cart.addToCart(product);
    }

    /**
     * Removes a product from the cart owned by the user owning the ProgramState
     * given the productID as a string.
     * 
     * @param productID
     * @throws RequestException
     */
    @Override
    public void removeFromCart(String productID) throws RequestException {
        IProduct product = currentUser.getProduct(productID);
        cart.removeFromCart(product);
    }

    @Override
    public void resetCart(String productID) throws RequestException {
        IProduct product = currentUser.getProduct(productID);
        cart.resetCart(product);
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

    /**
     * returns a list of product maps
     * @return
     * @throws RequestException
     */
    @Override
    public Set<Map<String, String>> getAllProducts() throws RequestException {
        Set<Map<String, String>> allProducts = new HashSet<>();
        for (IProduct p : currentUser.getAllProducts()) {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("Name", p.getName());
            tempMap.put("Price", p.getCost().toString());
            tempMap.put("Amount", p.getAmount());
            tempMap.put("Id", p.getID());
            allProducts.add(tempMap);

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
     * @throws RequestException
     */
    @Override
    public Map<String, String> getProduct(String productID) throws RequestException{
        IProduct tempProd = currentUser.getProduct(productID);
        Map<String, String> output = new HashMap<>();
        output.put("Name", tempProd.getName());
        output.put("Price", tempProd.getCost().toString());
        output.put("Amount", tempProd.getAmount());
        output.put("Id", productID);
        return output;

    }

    public void increaseProductAmount(String productID, int amount) throws RequestException{
        IProduct tempProd = currentUser.getProduct(productID);
        tempProd.increaseAmount(amount);
    }

    public String getName(){
        return currentUser.getName();
    }

    @Override
    public List<Map<String, String>> getOrderHistory() throws RequestException {
        List<Map<String, String>> temp = new ArrayList<>();
        for (List<IOrder> order : currentUser.getOrderHistory()) {
            
                
            for (IOrder o : order) {
                Map<String, String> tempMap = new HashMap<>();
                int productAmount = 0;
                String prev = "";
                for(IProduct product : o.getProducts()){
                    // System.out.println("curr " + product.getName());
                    // System.out.println("prev " + prev);

                    if(!prev.equals(product.getName())){
                        tempMap.put(product.getName(), "1");
                    } else {
                        productAmount = Integer.parseInt(tempMap.get(product.getName()));
                        productAmount ++;
                        tempMap.put(product.getName(), Integer.toString(productAmount));
                    }
                    prev = product.getName();
                    
                }
                temp.add(tempMap);
            }   
            
        }
        // System.out.println(temp);
        return temp;
    }
}