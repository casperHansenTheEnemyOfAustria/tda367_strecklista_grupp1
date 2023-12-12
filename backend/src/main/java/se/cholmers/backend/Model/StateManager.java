package se.cholmers.backend.Model;

import java.util.*;

import se.cholmers.backend.Model.Interfaces.IProgramState;
import se.cholmers.backend.Model.Interfaces.IUser;
import se.cholmers.backend.RequestException;

public class StateManager {
    private Map<String, IProgramState> states;
    private static StateManager instance;

    /**
     * Creates a new StateManager if it doesn't already exist.
     */
    public static StateManager getInstance() {
        if (StateManager.instance == null) {
            StateManager.instance = new StateManager();
        }
        return instance;
    }

    /**
     * Only to be used for singleton.
     */
    private StateManager() {
        this.states = new HashMap<>();
    }

    /**
     * Creates a new ProgramState when a user logs in and assigns the User if it's
     * properly logged in.
     * 
     * @param userName
     * @param password
     * @return A unique stateID for the programState.
     * @throws RequestException if the user login fails
     */
    public String login(String userName, String password) throws RequestException{
        IUser user = new User(userName, userName, password);
        IProgramState state = new ProgramState(user);

        String stateID = UUID.randomUUID().toString();
        states.put(stateID, state);
        String output = stateID;
        System.out.println("user login with id" + output);
        return output;
    }

    /**
     * Logs out the user by removing the state.
     * 
     * @param stateID
     * @return
     * @throws RequestException
     */
    public String logout(String stateID) throws RequestException {
        try {
            states.remove(stateID);
        } catch (NullPointerException e) {
            throw new RequestException(stateID + " is not a valid stateID");
        }
        
        return "log out successful";

    }

    /**
     * Gets the saldo from a state.
     * 
     * @param stateID
     * @param groupID
     * @return
     */
    public String getSaldo(String stateID, String groupID) {
        return states.get(stateID).getSaldo(groupID);
    }

    /**
     * Adds a product to the cart of a UserState.
     * 
     * @param stateID
     * @param productID
     * @throws RequestException
     */
    public void addToCart(String stateID, String productID) throws RequestException {
        states.get(stateID).addToCart(productID);
    }

    /**
     * Removes a productID from the cart of the given state.
     * 
     * @param stateID
     * @param productID
     * @throws RequestException
     */
    public void removeFromCart(String stateID, String productID) throws RequestException {
        states.get(stateID).removeFromCart(productID);
    }

    /**
     * Gets the contents of a cart from the given state.
     * 
     * @param stateID
     * @return
     */
    public Map<String, String> getCart(String stateID) {
        return states.get(stateID).getCart();
    }

    /**
     * Checks out the cart of a user given a stateID.
     * 
     * @param stateID
     * @throws RequestException
     */
    public void completePurchase(String stateID) throws RequestException {
        states.get(stateID).completePurchase();
    }

    /**
     * prerequisite: The stateID has to be valid
     * @param stateID
     * @return a list of the product ids
     * @throws RequestException
     */
    public Set<Map<String,String>> getAvailableProducts(String stateID) throws RequestException {
        return states.get(stateID).getAllProducts();
    } 

    /**
     * returns a map of the product info
     * @param stateID
     * @param productID
     * @return
     * @throws RequestException
     */
    public Map<String, String> getProduct(String stateID, String productID) throws RequestException{
        return states.get(stateID).getProduct(productID);
    }

    /**
     * Adds amounts to a product in a usergroup 
     * prerequisite: The stateID has to be valid and the user has to be in the usergroup and the product has to exist
     * @param stateID
     * @param productID
     * @param amount
     * @throws NumberFormatException
     * @throws RequestException
     * 
     */
    public void increaseProductAmount(String stateID, String productID, int amount) throws NumberFormatException, RequestException{
        //TODO update user saldo after increasing the amount
        states.get(stateID).increaseProductAmount(productID, amount);
    }

    /**
     * Idk
     * 
     * @param stateID
     * @return A string
     */
    private String getAuth(String stateID) {
        return "test";
    }
}
