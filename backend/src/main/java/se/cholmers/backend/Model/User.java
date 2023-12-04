package se.cholmers.backend.Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import se.cholmers.backend.Model.Interfaces.IUserGroup;
import se.cholmers.backend.RequestException;
import se.cholmers.backend.newDatabaseInterface;
import se.cholmers.backend.Interface.IDatabaseInterface;

/**
 * This class represents a user in the system. It contains information about the
 * user such as their name, nickname and phone number
 */
class User implements se.cholmers.backend.Model.Interfaces.IUser {
    private String id;
    private String name;
    private String nick;
    private String phoneNumber;
    private Map<String, Float> saldo = new HashMap<>();
    private Set<IUserGroup> groups = new HashSet<>();
    private IDatabaseInterface dbi = newDatabaseInterface.getInstance();

    /**
     * This is the constructor for when recreating users from the database
     * 
     * @param userID
     */
    public User(String userID) {
        this.id = userID;
        this.name = dbi.getUserName(userID);
        this.nick = dbi.getUserNick(userID);
        this.phoneNumber = dbi.getUserPhoneNumber(userID);
        addGroupsFromDatabase();
    }

    /**
     * This method adds the user's groups to the user from the database
     * 
     * @param userID
     * 
     */
    private void addGroupsFromDatabase() {
        // TODO add a catch for if there are no groups
        List<String> comitteeIds = dbi.getCommitteesOfUser(id);
        for (String param : comitteeIds) {
            groups.add(new UserGroup(param));
        }
    }

    /**
     * This is the constructor
     * it should be called when a new user is created and also add the
     * user's group to it read from the database
     * 
     * @param name
     * @param nick
     * 
     * @throws NullPointerException if the input is null
     */
    public User(String name, String nick, String password) throws RequestException{
        this.name = name;
        this.nick = nick;
        this.id = dbi.authenticateUser(nick, password);
        addGroupsFromDatabase();

    }

    @Override
    public void addUserToGroup(IUserGroup group) {
        try{
            dbi.putUserInCommittee(id, group.getID().toString(), 0f);
        } catch (RequestException e) {
            System.out.println(e.getMessage());
        }
        groups.add(group);
    }

    @Override
    public Float getSaldo(String groupID) {
        Float saldoFromDB = dbi.getSaldoFromUserInCommittee(id, groupID);
        saldo.put(groupID, saldoFromDB);
        return saldo.get(groupID);
    }

    @Override
    public void purchaseItem(Product product, Integer numberOfProducts) throws RequestException {
        Float currentSaldo = saldo.get(product.getGroupID());
        currentSaldo -= product.getCost() * numberOfProducts;
        saldo.put(product.getGroupID(), currentSaldo);

        try{
            dbi.updateUserSaldo(id, product.getGroupID(), currentSaldo.toString());
        }catch(NullPointerException e){
            throw new RequestException(id + "does not exist or" + product.getGroupID() + "does not exist");
        }catch(IllegalArgumentException e){
            throw new RequestException(id + "is not in " + product.getGroupID());
        }
        
    
    }

    @Override
    public Set<Product> getAllProducts() {
        Set<Product> allProducts = new HashSet<>();
        addGroupsFromDatabase();
        for (IUserGroup IUserGroup : groups) {
            allProducts.addAll(IUserGroup.getProducts());
        }
        return allProducts;
    }

    @Override
    public Product getProduct(String productID) {
        for (Product product : getAllProducts()) {
            System.out.println("Looking for product with ID: " + productID + " found product with ID: " + product.getID());
            if (product.getID().equals(productID)) {
                return product;
            }
        }
        return null;
    }
}