package se.cholmers.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import se.cholmers.backend.Interface.IDatabaseInterface;
import se.cholmers.backend.Model.StateManager;

/**
 * AdminController this controlls admin features such as creating users and
 * products and assignings these to groups
 * It talks directly to the database interface
 */
@RestController
public class AdminController {
    @Autowired
    StateManager stateManager;
    IDatabaseInterface dbi;

    public AdminController(StateManager stateManager, IDatabaseInterface dbi) {
        this.stateManager = stateManager;
        this.dbi = dbi;
    }

    /**
     * createUser creates a user in the database
     * 
     * @param userName (the users name)
     * @param password (the users wanted password)
     * @return response (a response with the correct code and a success message if
     *         the user was created. Otherwise it should havea an error code)
     * 
     */
    @RequestMapping(value = "/createUser/{userName}/{password}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> createUser(@PathVariable("userName") String userName,
            @PathVariable("password") String password) {
        try {
            // possiubly change to factory pattern??
            dbi.createUser(userName, "0734111337", userName, password);
            Response<String> response = new Response<String>("user created");
            return response;
        } catch (RequestException e) {
            return new Response<String>(null, e.getMessage());
        } finally {

        }
    }

    /**
     * createCommittee creates a committee in the database
     *
     * @param name (the name of the committee)
     * @param year (the year of the committee)
     * @return response (a response with the correct code and a success message if
     *         the committee was created. Otherwise it should havea an error code)
     */
    @RequestMapping(value = "/createCommittee/{name}/{year}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> createCommittee(@PathVariable("name") String name,
                                       @PathVariable("year") String year) {
        try {
            // possiubly change to factory pattern??
            dbi.createCommittee(name, year);
            Response<String> response = new Response<String>("Committee created");
            return response;
        } catch (RequestException e) {
            return new Response<String>(null, e.getMessage());
        } finally {

        }
    }

    /**
     * addUserToGroup adds a user to a group
     *
     * @param userNick
     * @param committeeID
     * @return response (a response with the correct code and a success message if
     *        the user was added to the group. Otherwise it should havea an error
     *        code)
     */
    @RequestMapping(value = "/addUserToGroup/{userNick}/{committeeID}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> addUserToGroup(@PathVariable("userNick") String userNick,
            @PathVariable("committeeID") String committeeID) {
        try {
            // possiubly change to factory pattern??
            dbi.putUserInCommittee(userNick, committeeID, 0f);
            Response<String> response = new Response<String>("User added to group");
            return response;
        } catch (RequestException e) {
            return new Response<String>(null, e.getMessage());
        } finally {

        }
    }

    /**
     * createProduct creates a product in the database
     * 
     * @param productName
     * @param price
     * @return response (a response with the correct code and a success message if
     *         the product was created. Otherwise it should havea an error code)
     */
    @RequestMapping(value = "/createProduct/{productName}/{price}/{committeeID}/{amount}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> createProduct(@PathVariable("productName") String productName,
            @PathVariable("price") String price, @PathVariable("committeeID") String committeeID,
            @PathVariable("amount") String amount) {
        try {
        
            // possiubly change to factory pattern??
            dbi.createProduct(productName, Float.parseFloat(price), committeeID, Integer.parseInt(amount));
            Response<String> response = new Response<String>("Product created");
            return response;
        } catch (RequestException e) {
            return new Response<String>(null, e.getMessage());
        } finally {

        }
    }

}
