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

// import org.springframework.*;

import java.util.Map;
@RestController
// @RequestMapping("/user")
public class UserController {
    @Autowired
    ProgramState ProgramState;
    public UserController(ProgramState ProgramState) {
        this.ProgramState = ProgramState;
    }

    @RequestMapping(value  = "/getSaldo/{sessionID}/{authToken}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Map<Group, Double>>getSaldo(@PathVariable("sessionID") String sessionID, @PathVariable("authToken") String authToken) {
        try{
            Response<Map<Group, Double>> saldo = new Response<Map<Group, Double>>(ProgramState.getSaldo(sessionID, authToken));
            return saldo;
        }catch(RequestException e){
            return new Response<Map<Group, Double>>(null, e.getMessage());
        }
        
    }

    @RequestMapping(value = "/createUser/{userName}/{password}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> createUser(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        try{
            //possiubly change to factory pattern??
            Response<String> response = new Response<String> (ProgramState.createUser(userName, password));
            return response;
        }catch(RequestException e){
            return new Response<String>(null, e.getMessage());
        }
    }

    @RequestMapping(value = "/login/{userName}/{password}", method = RequestMethod.GET)
    @ResponseBody
    public Response<String[]> login(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        try{
            Response<String[]> response = new Response<String[]> (ProgramState.login(userName, password));
            return response;
        }catch(RequestException e){
            return new Response<String[]>(null, e.getMessage());
        }
    }

    @RequestMapping(value = "/logout/{sessionID}", method = RequestMethod.POST)
    @ResponseBody
    public Response<String> logout(@PathVariable("sessionID") String sessionID) {
        try{
            Response<String> response = new Response<String> (ProgramState.logout(sessionID));
            return response;
        }catch(RequestException e){
            return new Response<String>(null, e.getMessage());
        }
    }

    @RequestMapping(value = "/getCart/{sessionID}" , method = RequestMethod.GET)
    @ResponseBody
    public Response<Map<Product, Integer>> getCart(@PathVariable("sessionID") String sessionID) {
        try{
            Response<Map<Product, Integer>> response = new Response<Map<Product, Integer>> (ProgramState.getCart(sessionID));
            return response;
        }catch(RequestException e){
            return new Response<Map<Product, Integer>>(null, e.getMessage());
        }
    }






}

//methods done for testing purposes
class ProgramState{
    public String[] login(String userName, String password) throws RequestException{
        String[] output = {"login", "authToken"};
        return output;
    }
    public String logout(String userName) throws RequestException{
        return "logout";
    }
    public String createUser(String userName, String password) throws RequestException{
        return "createUser";
    }
    public Map<Group, Double> getSaldo(String userID, String authToken) throws RequestException{
        return null;
    }
}

class Group{
    public String name;
    public String id;
    public Group(String name, String id){
        this.name = name;
        this.id = id;
    }
}


