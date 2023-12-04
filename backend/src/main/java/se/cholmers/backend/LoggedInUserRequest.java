package se.cholmers.backend;

import java.util.Map;

import se.cholmers.backend.Interface.iRequest;

public class LoggedInUserRequest implements iRequest {
    private String sessionID;
    private Map<String, String> data;
    
    public LoggedInUserRequest(String sessionID, Map<String, String> data) {
        this.sessionID = sessionID;
        this.data = data;
    }

    @Override
    public String getData(String key) throws RequestException{
        System.out.println(data);
        String out = data.get(key);
        if(out == null){
            throw new RequestException("No such key " + data.keySet().toString() + " please use" + key);
        }
        return out;
        
    }
    public String getSessionID(){
        return sessionID;
    }

}
