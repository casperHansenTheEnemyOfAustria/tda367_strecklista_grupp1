package se.cholmers.backend.Model;

import java.util.UUID;

class Product {
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