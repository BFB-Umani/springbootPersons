package com.inlamning.springbootPersons.models.response;

public class EditResponse {

    private String message;

    public EditResponse(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
