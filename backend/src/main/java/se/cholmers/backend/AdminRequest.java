package se.cholmers.backend;

import se.cholmers.backend.Interface.iRequest;

public class AdminRequest implements iRequest {
    private String data;

    public AdminRequest(String data) {

        this.data = data;
    }

    @Override
    public String getData(String key) throws RequestException {

        String out = data;
        if (out == null) {
            throw new RequestException("No such key");
        }
        return out;

    }
}
