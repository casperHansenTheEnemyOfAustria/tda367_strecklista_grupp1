package se.cholmers.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import se.cholmers.backend.Model.StateManager;

@RestController
public class AdminController {
    @Autowired
    StateManager stateManager;
    DatabaseInterface dbi;
    public AdminController(StateManager stateManager, DatabaseInterface dbi) {
        this.stateManager = stateManager;
    }

    @RequestMapping(value = "/createUser/{userName}/{password}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> createUser(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        try{
            //possiubly change to factory pattern??
            stateManager.createUser(userName, password);
            Response<String> response = new Response<String> ("user created");
            return response;
        }catch(RequestException e){
            return new Response<String>(null, e.getMessage());
        }
    }

    @RequestMapping(value = "/createProduct/{productName}/{price}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> createProduct(@PathVariable("productName") String productName, @PathVariable("price") String price) {
        try{
            //possiubly change to factory pattern??
            dbi.createProduct(productName, price);
            Response<String> response = new Response<String> ("Product created");
            return response;
        }catch(RequestException e){
            return new Response<String>(null, e.getMessage());
        }
    }

}
