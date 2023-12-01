package se.cholmers.backend;

import se.cholmers.backend.Interface.iRequest;

public class LoginRequest implements iRequest{
    private String userName;
    private String password;
    public LoginRequest(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    @Override
    public String getData(String key) throws RequestException {
        if(key.equals("userName")){
            return userName;
        }
        else if(key.equals("password")){
            return password;
        }
        else{
            throw new RequestException("No such key");
        }
    }
}
