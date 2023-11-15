package se.cholmers.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import se.cholmers.backend.Model.StateManager;

 /**
  * AdminController this controlls admin features such as creating users and products and assignings these to groups
  * It talks directly to the database interface
  */
@RestController
public class AdminController {
    @Autowired
    StateManager stateManager;
    DatabaseInterface dbi;
    public AdminController(StateManager stateManager, DatabaseInterface dbi) {
        this.stateManager = stateManager;
        this.dbi = dbi;
    }

  
/**
 * createUser creates a user in the database
 * @param userName (the users name)
 * @param password (the users wanted password)
 * @return response (a response with the correct code and a success message if the user was created. Otherwise it should havea an error code)
 * 
 */
    @RequestMapping(value = "/createUser/{userName}/{password}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> createUser(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        try{
            //possiubly change to factory pattern??
            // stateManager.createUser(userName, password);
            Response<String> response = new Response<String> ("user created");
            return response;
        // }catch(RequestException e){
        //     return new Response<String>(null, e.getMessage());
        }finally{

        }
    }

    /**
     * createProduct creates a product in the database
     * @param productName 
     * @param price
     * @return response (a response with the correct code and a success message if the product was created. Otherwise it should havea an error code)
     */
    @RequestMapping(value = "/createProduct/{productName}/{price}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> createProduct(@PathVariable("productName") String productName, @PathVariable("price") String price) {
        try{
            //possiubly change to factory pattern??
            // dbi.createProduct(productName, price);
            Response<String> response = new Response<String> ("Product created");
            return response;
        // }catch(RequestException e){
        //     return new Response<String>(null, e.getMessage());
        }finally{

        }
    }

}

//temporary interface
class DatabaseInterface{
    public static DatabaseInterface getInstance(){
        return null;
    }
}   