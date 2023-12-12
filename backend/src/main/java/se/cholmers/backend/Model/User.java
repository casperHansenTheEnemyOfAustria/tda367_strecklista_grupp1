package se.cholmers.backend.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import se.cholmers.backend.Model.Interfaces.IOrder;
import se.cholmers.backend.Model.Interfaces.IProduct;
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
     * @throws RequestException
     */
    public User(String userID) throws RequestException {
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
     * @throws RequestException
     * 
     */
    private void addGroupsFromDatabase() throws RequestException {
        // TODO add a catch for if there are no groups
        Set<String> comitteeIds = dbi.getCommitteesOfUser(id);
        groups = new HashSet<>();
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
    public void purchaseItem(IProduct product, Integer numberOfProducts) throws RequestException {
        Float currentSaldo = saldo.get(product.getGroupID());
        currentSaldo -= product.getCost() * numberOfProducts;
        saldo.put(product.getGroupID(), currentSaldo);

        try{
            dbi.updateUserSaldo(id, product.getGroupID(), currentSaldo.toString());
            product.decreaseAmount(numberOfProducts);
            List<IProduct> products = new ArrayList<>();
            for (int i = 0; i < numberOfProducts; i++) {
                products.add(product);
            }
            new Order(products, product.getGroupID(), id);
        }catch(NullPointerException e){
            throw new RequestException(id + "does not exist or" + product.getGroupID() + "does not exist");
        }catch(IllegalArgumentException e){
            throw new RequestException(id + "is not in " + product.getGroupID());
        }
        
    
    }

    /**
     * 
     * @return returns a set of all products the user has access to in all its
     *         usergorups
     * @throws RequestException
     * @throws NullPointerException if the user is not a member of any group
     */
    @Override
    public Set<IProduct> getAllProducts() throws RequestException {
        Set<IProduct> allProducts = new HashSet<>();
        addGroupsFromDatabase();
        for (IUserGroup userGroup : groups) {
            allProducts.addAll(userGroup.getAllProducts());
        }
        return allProducts;
    }

    /**
     * 
     * @param productID
     * @return the product with the given productID logged in its usergroup's
     *         database
     * @throws RequestException
     * @throws NullPointerException if the user is not a member of any group
     */
    @Override
    public IProduct getProduct(String productID) throws RequestException {
        for (IProduct product : getAllProducts()) {
            if (product.getID() == productID) {
                return product;
            }
        }
        return null;
    }

    /**
     * this method gets a users order history from all its groups
     * @return a list of lists of orders'
     * @throws RequestException if the user is not a member of any group
     */
    public List<List<IOrder>> getOrderHistory() throws RequestException {
        addGroupsFromDatabase();
        List<List<IOrder>> orderHistory = new ArrayList<>();
        for(IUserGroup group : groups){
            orderHistory.add(group.getOrderHistory(id));
        }
        return orderHistory;
    }

    public String getID() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getNick(){
        return nick;
    }

}