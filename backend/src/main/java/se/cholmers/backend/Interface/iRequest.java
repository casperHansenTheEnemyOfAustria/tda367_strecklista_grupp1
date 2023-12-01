package se.cholmers.backend.Interface;

import java.util.Map;

import se.cholmers.backend.RequestException;

public interface iRequest {


    public String getData(String key) throws RequestException;

}
