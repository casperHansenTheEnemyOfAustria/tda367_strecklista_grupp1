package se.cholmers.backend.Model.Interfaces;

import se.cholmers.backend.RequestException;

public interface IProduct {
    /**
     * @return the groupID of the UserGroup a certain product belongs to.
     */
    String getGroupID();

    /**
     * @return the cost of a certain product.
     */
    Float getCost();

    /**
     * Increases the amount of a product by a certain amount.
     * precondition: The amount has to be positive
     *
     * @param amount
     * @throws RequestException postcondition: The amount of the product is increased by the given amount
     */
    void increaseAmount(int amount) throws RequestException;

    /**
     * Decreases the amount of a product by a certain amount. Should be called upon purchase.
     * precondition: The amount has to be positive
     *
     * @param amount
     * @throws RequestException postcondition: The amount of the product is decreased by the given amount
     */
    void decreaseAmount(int amount) throws RequestException;

    void increaseAmount();

    /**
     * Gets the ID of a product
     *
     * @return the ID of the product
     */
    String getID();

    /**
     * Gets the name of a product
     *
     * @return the name of the product
     */
    String getName();

    /**
     * Gets the amount of a product
     * @return
     */
    public String getAmount();
}
