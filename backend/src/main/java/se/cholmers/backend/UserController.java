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

import se.cholmers.backend.Model;
import se.cholmers.backend.Model.UserGroup;
import se.cholmers.backend.Model.Cart;
import se.cholmers.backend.Model.StateManager;

// import org.springframework.*;

import java.util.Map;
@RestController
// @RequestMapping("/user")
public class UserController {
    @Autowired
    StateManager stateManager;
    public UserController(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @RequestMapping(value  = "/getSaldo/{sessionID}/{authToken}", method = RequestMethod.GET)
    @ResponseBody
    public Response<String>getSaldo(@PathVariable("sessionID") String sessionID, @PathVariable("authToken") String authToken) {
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

    @RequestMapping(value = "/getCart/{sessionID}" , method = RequestMethod.GET)
    @ResponseBody
    public Response<Cart> getCart(@PathVariable("sessionID") String sessionID) {
        try{
            Response<Cart> response = new Response<Cart> (stateManager.getCart(sessionID));
            return response;
        // }catch(RequestException e){
        //     return new Response<Cart>(null, e.getMessage());
        }finally{

        }
    }
    
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




}



