package com.tekion.gameofcricket.utility.exceptionhandling;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException(String message) {
        super(message);
    }
}
