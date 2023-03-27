package com.tekion.gameofcricket.utility.exception;

/**
 * This is a user-defined exception for object id related anomalies
 */
public final class InvalidObjectIdException extends RuntimeException {
    private final String title;
    public InvalidObjectIdException(String title, String message) {
        super(message);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}