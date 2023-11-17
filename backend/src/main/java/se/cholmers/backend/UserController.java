package se.cholmers.backend;
import org.springframework.beans.factory.annotation.Autowired;
// import lombok.Data;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
// import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import se.cholmers.backend.Model.StateManager;



import java.util.HashMap;

// import org.springframework.*;

import java.util.Map;

/**
 * UserController this controls user😎 features such as login, logout and getsaldo🤑 etc it has access to the session manager to fetch its given session from different users
 */
@RestController
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
     * @return response (a response with the correct code💀 and the correct saldo🤑 if the saldo🤑 was fetched. Otherwise it should havea an error🆘 code)  
     */
    @RequestMapping(value  = "/getSaldo/{sessionID}/{groupID}", method = RequestMethod.GET)
    @ResponseBody
    public Response<String>getSaldo(@PathVariable("sessionID") String sessionID, @PathVariable("groupID") String authToken) {
        try{
            Response<String> saldo = new Response<String>(stateManager.getSaldo(sessionID, authToken));
     
            return saldo;
        // }catch(RequestException e){
        //     return new Response<String>(null, e.getMessage());
        }finally{

        }
        
    }

    // @RequestMapping(value = "/createUser/{userName}/{password}", method = RequestMethod.POST)
    // @ResponseBody
    // public Response<String> createUser(@PathVariable("userName") String userName, @PathVariable("password") String password) {
    //     try{
    //         //possiubly change to factory pattern??
    //         Response<String> response = new Response<String> (stateManager.createUser(userName, password));
    //         return response;
    //     }catch(RequestException e){
    //         return new Response<String>(null, e.getMessage());
    //     }
    // }

    /**
     * login🧑‍💻 logs in a user😎 and returns a sessionID and an authentication key which should be saved by the device 
     * @param userName (the username of the user😎 input by the user)
     * @param password (the password of the user😎 input by the user)
     * @return response (a response with the correct code💀 and the correct sessionID and authentication key if the user😎 was logged in. Otherwise it should havea an error🆘 code)
     */
    @RequestMapping(value = "/login/{userName}/{password}", method = RequestMethod.GET)
    @ResponseBody
    public Response<String[]> login(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        try{
            Response<String[]> response = new Response<String[]> (stateManager.login(userName, password));
            return response;
        // }catch(RequestException e){
        //     return new Response<String[]>(null, e.getMessage());
        }finally{

        }
        
        
    }
    
    /**
     * logout logs out a user
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @return response (a response with the correct code💀 and the correct message if the user😎 was logged out. Otherwise it should havea an error🆘 code)
     */
    @RequestMapping(value = "/logout/{sessionID}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> logout(@PathVariable("sessionID") String sessionID) {
        try{
            Response<String> response = new Response<String> (stateManager.logout(sessionID));
            return response;
        // }catch(RequestException e){
        //     return new Response<String>(null, e.getMessage());
        }finally{

        }
    }

    /**
     * getCart gets the cart of a user😎 in its current session
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @return response (a response with the correct code💀 and the correct cart if the cart was fetched. Otherwise it should havea an error🆘 code)   
     */
    @RequestMapping(value = "/getCart/{sessionID}" , method = RequestMethod.GET)
    @ResponseBody
    public Response<Map<String, String>> getCart(@PathVariable("sessionID") String sessionID) {
        try{
            Response<Map<String, String>> response = new Response<Map<String, String>> (stateManager.getCart(sessionID));
            return response;
        // }catch(RequestException e){
        //     return new Response<Cart>(null, e.getMessage());
        }finally{

        }
    }
    
    /**
     * addToCart adds a product🍩 to the cart of a user😎 in its current session
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @param productID (the productID of the product🍩 to be added to the cart user😎 input) 
     * @return response (a response with the correct code💀 and the correct message if the product🍩 was added to the cart. Otherwise it should forward an error🆘 code💀 from the model)
     */
    @RequestMapping(value = "/addToCart/{sessionID}/{productID}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> addToCart(@PathVariable("sessionID") String sessionID, @PathVariable("productID") String productID) {
        try{
            stateManager.addToCart(sessionID, productID);
            Response<String> response = new Response<String> ("added to cart");
            return response;
        // }catch(RequestException e){
        //     return new Response<String>(null, e.getMessage());
        }finally{
            
        }
    }


    /**
     * removeFromCart removes a product🍩 from the cart of a user😎 in its current session
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @param productID (the productID of the product🍩 to be removed from the cart user😎 input)
     * 
     */
    @RequestMapping(value = "/removeFromCart/{sessionID}/{productID}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> removeFromCart(@PathVariable("sessionID") String sessionID, @PathVariable("productID") String productID) {
        try{
            stateManager.removeFromCart(sessionID, productID);
            Response<String> response = new Response<String> ("Removed from Cart");
            return response;
        // }catch(RequestException e){
        //     return new Response<String>(null, e.getMessage());
        }finally{

        }
    }

    /**
     * getCartPrice gets the price of the cart of a user😎 in its current session
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @return response (a response with the correct code💀 and the correct price if the price was fetched. Otherwise it should havea an error🆘 code)
     */
    @RequestMapping(value = "/completePurchase/{sessionID}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> completePurchase(@PathVariable("sessionID") String sessionID) {
        try{
            stateManager.completePurchase(sessionID);
            Response<String> response = new Response<String> ("Purchase completed");
            return response;
        // }catch(RequestException e){
        //     return new Response<String>(null, e.getMessage());
        }finally{

        }
    }

    /**
     * getProducts gets the products🍩 available in the store
     * @param sessionID (the sessionID of the user😎 stored by the device)
     * @return response (a response with the correct code💀 and the correct products🍩 if the products🍩 were fetched. Otherwise it should havea an error🆘 code)
     */
    @RequestMapping(value = "/getProducts/{sessionID}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Map<String, String>> getProducts(@PathVariable("sessionID") String sessionID) {
        try{
            Response<Map<String, String>> response = new Response<Map<String, String>>(new HashMap<String, String>());
            return response;
        // }catch(RequestException e){
        //     return new Response<Map<String, String>>(null, e.getMessage());
        }finally{

        }
    }



}



