package se.cholmers.backend;

public class Response<T> {
    private T data;
    private String responseCode;

    public Response( T data, String responseCode) {
        this.data = data;
        this.responseCode = responseCode;
    }

    public Response(T data) {
        this.data = data;
        this.responseCode = "200";
    }

    public T getData() {
        return data;
    }

    public String getResponseCode() {
        return responseCode;
    }
}
