package se.cholmers.backend.Model;

import java.util.UUID;

import se.cholmers.backend.DatabaseInterface;
import se.cholmers.backend.RequestException;

class Product {
    private DatabaseInterface dbi = DatabaseInterface.getInstance();
    private int amount;
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
        this.amount = 0;

        //catches the already exists error and does nothing since its already been created
        try {
            dbi.createProduct(name, cost.toString(), groupID, "0");
        } catch (RequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Creates a new product with a given ID.
     * 
     * @param productID (the ID of the product.)
     */
    public Product(String productID, String groupID) {
        this.productID = productID;
        this.name = dbi.getProductName(productID);
        this.cost = dbi.getProductPrice(productID);
        this.groupID = groupID;
        this.amount = dbi.getProductAmount(productID);    
    }

    /**
     * 
     * @return the groupID of the UserGroup a certain product belongs to.
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * 
     * @return the cost of a certain product.
     */
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

    public String getName() {
        return name;
    }
}