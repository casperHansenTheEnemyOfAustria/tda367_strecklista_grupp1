package se.cholmers.backend.Model;

import java.util.UUID;

import org.yaml.snakeyaml.events.Event.ID;

import se.cholmers.backend.DatabaseInterface;
import se.cholmers.backend.RequestException;
import se.cholmers.backend.newDatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;

class Product {
    private IDatabaseInterface dbi = newDatabaseInterface.getInstance();
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
     * @throws RequestException
     */
    public Product(String name, Float cost, String groupID) throws RequestException {
        this.name = name;
        this.cost = cost;
        this.groupID = groupID;
        this.amount = 0;

        //catches the already exists error and does nothing since its already been created
      
            dbi.createProduct(name, cost, groupID, 0);
        
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
     * Increases the amount of a product by a certain amount.
     * precondition: The amount has to be positive
     * @param amount
     * @throws RequestException
     * postcondition: The amount of the product is increased by the given amount
     */
    public void increaseAmount(int amount) throws RequestException {
        if (amount < 0) {
            throw new RequestException("Amount cannot be negative");
        }
    }

    /**
     * Decreases the amount of a product by a certain amount. Should be called upon purchase.
     * precondition: The amount has to be positive
     * @param amount
     * @throws RequestException
     * postcondition: The amount of the product is decreased by the given amount
     */
    public void decreaseAmount(int amount) throws RequestException {
        if (amount < 0) {
            throw new RequestException("Amount cannot be negative");
        }
    }

    public void increaseAmount() {
        amount++;
        dbi.updateProductAmount(amount, productID);
    }

    /**
     * Gets the ID of a product
     * 
     * @return the ID of the product
     */
    public String getID() {
        return productID;
    }

    /**
     * Gets the name of a product
     * 
     * @return the name of the product
     */
    public String getName() {
        return name;
    }
}