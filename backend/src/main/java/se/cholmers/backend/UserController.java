package se.cholmers.backend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import lombok.Data;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
// import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import se.cholmers.backend.Model.StateManager;

import java.util.*;

// import org.springframework.*;

import org.springframework.web.bind.annotation.RequestParam;


/**
 * UserController this controls user😎 features such as login, logout and getsaldo🤑 etc it has access to the session manager to fetch its given session from different users
 */
@RestController
@CrossOrigin
// @RequestMapping("/user")
public class UserController {
    @Autowired
    StateManager stateManager;
    public UserController(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    /**
     * getSaldo🤑 gets the saldo🤑 of a user😎 in  the context of a group
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @param groupID (the groupID of the group the user😎 is in)
     * @return ResponseEntity (a ResponseEntity with the correct code💀 and the correct saldo🤑 if the saldo🤑 was fetched. Otherwise it should havea an error🆘 code)  
     */
    @RequestMapping(value  = "/getSaldo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String>getSaldo(@RequestBody LoggedInUserRequest freq) {
        try{
            String sessionID = freq.getSessionID();
            String groupId = freq.getData("groupId");
            ResponseEntity<String> saldo = new ResponseEntity<String>(stateManager.getSaldo(sessionID, groupId), HttpStatus.OK);
            return saldo;
        }catch(RequestException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }finally{

        }
        
    }

    // @RequestMapping(value = "/createUser/{userName}/{password}", method = RequestMethod.POST)
    // @ResponseBody
    // public ResponseEntity<String> createUser(@PathVariable("userName") String userName, @PathVariable("password") String password) {
    //     try{
    //         //possiubly change to factory pattern??
    //         ResponseEntity<String> ResponseEntity = new ResponseEntity<String> (stateManager.createUser(userName, password));
    //         return ResponseEntity;
    //     }catch(RequestException e){
    //         return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    //     }
    // }

    /**
     * login🧑‍💻 logs in a user😎 and returns a sessionID and an authentication key which should be saved by the device 
     * @param userName (the username of the user😎 input by the user)
     * @param password (the password of the user😎 input by the user)
     * @return ResponseEntity (a ResponseEntity with the correct code💀 and the correct sessionID and authentication key if the user😎 was logged in. Otherwise it should havea an error🆘 code)
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody LoginRequest freq) {
        
        try{
            String userName =  freq.getData("userName");
            System.out.println(userName);
            String password =  freq.getData("password");
            System.out.println(password);
            ResponseEntity<String> ResponseEntity = new ResponseEntity<String> (stateManager.login(userName, password), HttpStatus.OK);
            return ResponseEntity;
        }catch(RequestException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }finally{

        }
        
        
    }
    
    /**
     * logout logs out a user
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @return ResponseEntity (a ResponseEntity with the correct code💀 and the correct message if the user😎 was logged out. Otherwise it should havea an error🆘 code)
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> logout(@RequestBody LoggedInUserRequest freq) {
        String sessionID = freq.getSessionID();
        try{

            ResponseEntity<String> ResponseEntity = new ResponseEntity<String> (stateManager.logout(sessionID), HttpStatus.OK);
            return ResponseEntity;
        }catch(RequestException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }finally{

        }
    }

    /**
     * getCart gets the cart of a user😎 in its current session
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @return ResponseEntity (a ResponseEntity with the correct code💀 and the correct cart if the cart was fetched. Otherwise it should havea an error🆘 code)   
     */
    @RequestMapping(value = "/getCart" , method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> getCart(@RequestBody LoggedInUserRequest freq) {
        String sessionID = freq.getSessionID();
        try{
            ResponseEntity<Map<String, String>> ResponseEntity = new ResponseEntity<Map<String, String>> (stateManager.getCart(sessionID), HttpStatus.OK);
            return ResponseEntity;
        // }catch(RequestException e){
        //     return new ResponseEntity<Cart>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }finally{

        }
    }
    
    /**
     * addToCart adds a product🍩 to the cart of a user😎 in its current session
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @param productID (the productID of the product🍩 to be added to the cart user😎 input) 
     * @return ResponseEntity (a ResponseEntity with the correct code💀 and the correct message if the product🍩 was added to the cart. Otherwise it should forward an error🆘 code💀 from the model)
     */
    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addToCart(@RequestBody LoggedInUserRequest freq) {
        String sessionID = freq.getSessionID();
        
        try{
            String productID = freq.getData("productID");
            stateManager.addToCart(sessionID, productID);
            ResponseEntity<String> ResponseEntity = new ResponseEntity<String> ("added to cart", HttpStatus.OK);
            return ResponseEntity;
        }catch(RequestException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }finally{
            
        }
    }


    /**
     * removeFromCart removes a product🍩 from the cart of a user😎 in its current session
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @param productID (the productID of the product🍩 to be removed from the cart user😎 input)
     * 
     */
    @RequestMapping(value = "/removeFromCart", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> removeFromCart(@RequestBody LoggedInUserRequest freq) {
        String sessionID = freq.getSessionID();
        try{
            String productID = freq.getData("productID");
            stateManager.removeFromCart(sessionID, productID);
            ResponseEntity<String> ResponseEntity = new ResponseEntity<String> ("Removed from Cart", HttpStatus.OK);
            return ResponseEntity;
        }catch(RequestException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }finally{

        }
    }

    /**
     * resetCart removes all of one product🍩 from the cart of a user😎 in its current session
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * 
     */
    @RequestMapping(value = "/resetCart", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> resetCart(@RequestBody LoggedInUserRequest freq) {
        String sessionID = freq.getSessionID();
        try{
            String productID = freq.getData("productID");
            stateManager.resetCart(sessionID, productID);
            ResponseEntity<String> ResponseEntity = new ResponseEntity<String> ("Removed all from Cart", HttpStatus.OK);
            return ResponseEntity;
        }catch(RequestException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }finally{

        }
    }

    /**
     * getCartPrice gets the price of the cart of a user😎 in its current session
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @return ResponseEntity (a ResponseEntity with the correct code💀 and the correct price if the price was fetched. Otherwise it should havea an error🆘 code)
     */
    @RequestMapping(value = "/completePurchase", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> completePurchase(@RequestBody LoggedInUserRequest freq) {
        String sessionID = freq.getSessionID();
        try{
            stateManager.completePurchase(sessionID);

            ResponseEntity<String> ResponseEntity = new ResponseEntity<String> ("Purchase completed",  HttpStatus.OK);
            return ResponseEntity;
        }catch(RequestException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);


        }finally{

        }
    }

    /**
     * getProducts gets the products🍩 available in the store
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @return ResponseEntity (a ResponseEntity with the correct code💀 and the correct products🍩 if the products🍩 were fetched. Otherwise it should havea an error🆘 code)
     */
    @RequestMapping(value = "/getProducts", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Set<Map<String,String>>> getProducts(@RequestBody LoggedInUserRequest freq) {
        String sessionID = freq.getSessionID();
        
        try{
            ResponseEntity<Set<Map<String,String>>> ResponseEntity = new ResponseEntity<Set<Map<String,String>>>(stateManager.getAvailableProducts(sessionID),HttpStatus.OK);
            // ResponseEntity<Map<String, String>> ResponseEntity = new ResponseEntity<Map<String, String>>(stateManager.getProducts(sessionID));
            return ResponseEntity;
        }catch(RequestException e){
            return new ResponseEntity<Set<Map<String, String>>>(HttpStatus.BAD_REQUEST);
        }finally{

        }
    }

    /**
     * getProduct gets a product🍩 from the database arranged as a map of its features, name, amount, cost and id
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @param productID (the productID of the product🍩 to be fetched user😎 input)
     * @return ResponseEntity (a ResponseEntity with the correct code💀 and the correct product🍩 if the product🍩 was fetched. Otherwise it should havea an error🆘 code)
     */
    @RequestMapping(value = "/getProduct", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> getProduct(@RequestBody LoggedInUserRequest freq) {
        String sessionID = freq.getSessionID();
        try{
            String productID = freq.getData("productID");
            ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity<Map<String, String>>(stateManager.getProduct(sessionID, productID), HttpStatus.OK);
            return responseEntity;
        }catch(RequestException e){
            return new ResponseEntity<Map<String, String>>(new HashMap<String, String>(), HttpStatus.BAD_REQUEST);
        }finally{

        }
    }

    @RequestMapping(value="/increaseProductAmount", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addToProduct(@RequestBody LoggedInUserRequest freq) {
        String sessionID = freq.getSessionID();
        try{
            String productID = freq.getData("productID");
            String amount = freq.getData("amount");
            stateManager.increaseProductAmount(sessionID, productID, Integer.parseInt(amount));
            return new ResponseEntity<String>("added", HttpStatus.OK);
        }catch(RequestException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }finally{

        }
    }

    @RequestMapping(value="/getName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getName(@RequestBody LoggedInUserRequest freq) {
        String sessionID = freq.getSessionID();
        String name = stateManager.getName(sessionID);
        return new ResponseEntity<String>(name, HttpStatus.OK);
    }
}



