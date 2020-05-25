package com.inlamning.springbootPersons.models.response;

public class AddResponse {
    private String message;


    public AddResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
