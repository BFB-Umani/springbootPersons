package com.inlamning.springbootPersons.models.response;

public class DeleteResponse {

    private String message;

    public DeleteResponse(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
