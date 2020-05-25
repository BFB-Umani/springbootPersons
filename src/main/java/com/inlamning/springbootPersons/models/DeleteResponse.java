package com.inlamning.springbootPersons.models;

public class DeleteResponse {

    private String message;
    private boolean bookDeleted;

    public DeleteResponse(String message, boolean bookDeleted) {
        this.message = message;
        this.bookDeleted = bookDeleted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isBookDeleted() {
        return bookDeleted;
    }

    public void setBookDeleted(boolean bookDeleted) {
        this.bookDeleted = bookDeleted;
    }
}
