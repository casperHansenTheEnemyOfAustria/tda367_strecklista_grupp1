package se.cholmers.backend.Model;

import java.util.HashMap;
import java.util.Map;
import se.cholmers.backend.Model.Interfaces.ICart;
import se.cholmers.backend.Model.Interfaces.IProduct;

class Cart implements ICart {
    private Map<IProduct, Integer> itemsInCart;

    /**
     * Constructor for Cart, initializes the internal Map.
     */
    public Cart() {
        itemsInCart = new ProductIntMap();
    }

    @Override
    public void addToCart(IProduct product) {
   
        
        Integer currentInCart;
        currentInCart = itemsInCart.get(product);
        try {   
            itemsInCart.put(product, currentInCart + 1);
        } catch (NullPointerException e) {
            itemsInCart.put(product, 1);
        }
        // System.out.println(itemsInCart.get(product));s
    }

    @Override
    public Map<IProduct, Integer> getCart() {
        return itemsInCart;
    }

    @Override
    public void removeFromCart(IProduct product) {
        Integer currentInCart = itemsInCart.get(product);
        try {
            if (currentInCart > 0) {
                itemsInCart.put(product, currentInCart - 1);
            }
        } catch (NullPointerException e) {
            // TODO: handle exception
        }

    }

    @Override
    public void resetCart(IProduct product) {
        Integer currentInCart = itemsInCart.get(product);
        try {
            if (currentInCart > 0) {
                itemsInCart.put(product, currentInCart = 0);
            }
        } catch (NullPointerException e) {
            // TODO: handle exception
        }

    }


    public Map<String, String> toStringMap() {
        Map<String, String> output = new HashMap<String, String>();
        for (IProduct p : itemsInCart.keySet()) {
            try {
                output.put(p.getID(), itemsInCart.get(p).toString());

          
            } catch (NullPointerException e) {
     
            }
        }
        return output;
    }

    @Override
    public void empty() {
        this.itemsInCart = new HashMap<>();
    }
}