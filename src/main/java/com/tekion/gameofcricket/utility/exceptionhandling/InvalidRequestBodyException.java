package com.tekion.gameofcricket.utility.exceptionhandling;

public class InvalidRequestBodyException extends RuntimeException {

    public InvalidRequestBodyException(String message) {
        super(message);
    }
}
