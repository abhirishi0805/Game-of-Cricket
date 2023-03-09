package com.tekion.gameofcricket.utility.exceptionhandling;

public class InvalidObjectIdException extends RuntimeException {
    private final String title;
    public InvalidObjectIdException(String title, String message) {
        super(message);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}