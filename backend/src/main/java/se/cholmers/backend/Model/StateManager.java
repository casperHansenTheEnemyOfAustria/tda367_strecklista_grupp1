package se.cholmers.backend.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StateManager {
    private Map<String, ProgramState> states;
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
     */
    public String[] login(String userName, String password) {
        User user = new User(userName, userName, password);
        ProgramState state = new ProgramState(user);

        String stateID = UUID.randomUUID().toString();
        states.put(stateID, state);
        String[] output = { stateID, getAuth(stateID) };
        return output;
    }

    /**
     * Logs out the user by removing the state.
     * 
     * @param stateID
     * @return
     */
    public String logout(String stateID) {
        states.remove(stateID);
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
     */
    public void addToCart(String stateID, String productID) {
        states.get(stateID).addToCart(productID);
    }

    /**
     * Removes a productID from the cart of the given state.
     * 
     * @param stateID
     * @param productID
     */
    public void removeFromCart(String stateID, String productID) {
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
     */
    public void completePurchase(String stateID) {
        states.get(stateID).completePurchase();
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
