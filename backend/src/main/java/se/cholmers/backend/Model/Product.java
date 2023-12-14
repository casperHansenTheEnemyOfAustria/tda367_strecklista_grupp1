package se.cholmers.backend.Model;

import java.util.UUID;

import org.yaml.snakeyaml.events.Event.ID;

import se.cholmers.backend.DatabaseInterface;
import se.cholmers.backend.RequestException;
import se.cholmers.backend.newDatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;
import se.cholmers.backend.Model.Interfaces.IProduct;

public class Product implements IProduct {
    private IDatabaseInterface dbi = newDatabaseInterface.getInstance();
    private Integer amount;
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
        try {
            dbi.createProduct(name, cost, groupID, 0);
        } catch (NullPointerException e){
            throw new RequestException("Product can't contatin null values");
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

    @Override
    public String getGroupID() {
        return groupID;
    }

    @Override
    public Float getCost() {
        return cost;
    }
 
    @Override
    public void increaseAmount(Integer amount) throws RequestException {
        if (amount < 0) {
            throw new RequestException("Amount cannot be negative");
        }
        this.amount+=amount;
        dbi.updateProductAmount(productID, this.amount);
    }

    @Override
    public void decreaseAmount(Integer amount) throws RequestException {
        if (amount < 0) {
            throw new RequestException("Amount cannot be negative");
        }
        this.amount-=amount;
        dbi.updateProductAmount(productID, this.amount);
    }

    @Override
    public void increaseAmount() {
        amount++;
        dbi.updateProductAmount(productID, amount);
    }

    @Override
    public String getID() {
        return productID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAmount(){
        dbi.updateProductAmount(productID, amount);
        return amount.toString();
    }

    @Override
    public boolean equals(Object o){
        if(o.getClass() != this.getClass()){
            return false;
        }
        System.out.println("equals");
        Product p = (Product) o;
        return p.getID().equals(this.getID());

    }
}