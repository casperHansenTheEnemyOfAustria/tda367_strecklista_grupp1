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
     * Singleton, only to be used once in getInstance.
     */
    private StateManager() {
        this.states = new HashMap<>();
    }

    public String[] login(String userName, String password) {
        User user = new User(userName, password);
        ProgramState state = new ProgramState(user);

        String stateID = UUID.randomUUID().toString();
        states.put(stateID, state);
        String[] output = { stateID, getAuth(stateID) };
        return output;
    }

    public String logout(String stateID) {
        states.remove(stateID);
        return "log out successful";

    }

    public String getSaldo(String stateID, String groupID) {
        return states.get(stateID).getSaldo(groupID);
    }

    public void addToCart(String stateID, String productID) {
        states.get(stateID).addToCart(productID);
    }

    public void removeFromCart(String stateID, String productID) {
        states.get(stateID).removeFromCart(productID);
    }

    public Map<Product, Integer> getCart(String stateID) {
        return states.get(stateID).getCart();
    }

    public void completePurchase(String stateID) {
        states.get(stateID).completePurchase();
    }

    private String getAuth(String stateID) {
        return "test";
    }
}
