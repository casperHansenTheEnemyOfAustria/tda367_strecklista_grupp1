package se.cholmers.backend;

import java.util.Map;

import se.cholmers.backend.Interface.iRequest;

public class AdminRequest implements iRequest {
    public String auth;
    private Map<String, String> data;

    public AdminRequest(String auth, Map<String, String> data) {
        this.auth = auth;
        this.data = data;
    }

    @Override
    public String getData(String key) throws RequestException {

        String out = data.get(key);
        if (out == null) {
            throw new RequestException("No such key" + data.keySet().toString() + "please use" + key);
        }
        return out;

    }
}
