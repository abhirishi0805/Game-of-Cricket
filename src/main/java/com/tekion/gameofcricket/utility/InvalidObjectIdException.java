package com.tekion.gameofcricket.utility;

public class InvalidObjectIdException extends RuntimeException {
    private final String title;
    public InvalidObjectIdException(String title, String description) {
        super(description);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
