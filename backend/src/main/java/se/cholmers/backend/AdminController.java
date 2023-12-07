package se.cholmers.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import se.cholmers.backend.Interface.IDatabaseInterface;
import se.cholmers.backend.Model.StateManager;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * AdminController this controlls admin features such as creating users and
 * products and assignings these to groups
 * It talks directly to the database interface
 */
@RestController
@CrossOrigin
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
     * @return ResponseEntity (a ResponseEntity with the correct code and a success message if
     *         the user was created. Otherwise it should havea an error code)
     * 
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createUser(@RequestBody AdminRequest freq) {
        
        try {
            String userName = freq.getData("userName");
            String password = freq.getData("password");
            // possiubly change to factory pattern??
            dbi.createUser(userName, "0734111337", userName, password);
            ResponseEntity<String> ResponseEntity = new ResponseEntity<String>("user created", HttpStatus.OK);
            return ResponseEntity;
        } catch (RequestException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } finally {

        }
    }

    /**
     * createCommittee creates a committee in the database
     *
     * @param name (the name of the committee)
     * @param year (the year of the committee)
     * @return ResponseEntity (a ResponseEntity with the correct code and a success message if
     *         the committee was created. Otherwise it should havea an error code)
     */
    @RequestMapping(value = "/createCommittee", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createCommittee(@RequestBody AdminRequest freq) {
        
        try {
            String name = freq.getData("name");
            String year = freq.getData("year");
            // possiubly change to factory pattern??
            dbi.createCommittee(name, year);
            ResponseEntity<String> ResponseEntity = new ResponseEntity<String>("Committee created", HttpStatus.OK);
            return ResponseEntity;
        } catch (RequestException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } finally {

        }
    }

    /**
     * addUserToGroup adds a user to a group
     *
     * @param userNick
     * @param committeeID
     * @return ResponseEntity (a ResponseEntity with the correct code and a success message if
     *        the user was added to the group. Otherwise it should havea an error
     *        code)
     */
    @RequestMapping(value = "/addUserToGroup", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addUserToGroup(@RequestBody AdminRequest freq) {
        try {
            String userNick = freq.getData("userNick");
            String committeeID = freq.getData("committeeID");
            // possiubly change to factory pattern??
            dbi.putUserInCommittee(userNick, committeeID, 0f);
            ResponseEntity<String> ResponseEntity = new ResponseEntity<String>("User added to group", HttpStatus.OK);
            return ResponseEntity;
        } catch (RequestException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } finally {

        }
    }

    /**
     * createProduct creates a product in the database
     * 
     * @param productName
     * @param price
     * @return ResponseEntity (a ResponseEntity with the correct code and a success message if
     *         the product was created. Otherwise it should havea an error code)
     */
    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createProduct(@RequestBody AdminRequest freq) {
        try {
        
            String productName = freq.getData("productName");
            String price = freq.getData("price");
            String committeeID = freq.getData("committeeID");
            String amount = freq.getData("amount");
            // possiubly change to factory pattern??
            dbi.createProduct(productName, Float.parseFloat(price), committeeID, Integer.parseInt(amount));
            ResponseEntity<String> ResponseEntity = new ResponseEntity<String>("Product created", HttpStatus.OK);
            return ResponseEntity;
        } catch (RequestException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } finally {

        }
    }

    @RequestMapping(value = "/updateProductAmount", method = RequestMethod.POST )
    @ResponseBody
    public ResponseEntity<String> updateProductAmount(@RequestBody AdminRequest freq) {
        try {
            String productID = freq.getData("productID");
            String amount = freq.getData("amount");
            dbi.updateProductAmount(productID, amount);
            ResponseEntity<String> ResponseEntity = new ResponseEntity<String>("Product amount updated", HttpStatus.OK);
            return ResponseEntity;
        } catch (RequestException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } finally {

        }
    }
    

}
