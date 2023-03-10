package com.tekion.gameofcricket.utility.exceptionhandling;

/**
 * This is a user-defined exception for client sending invalid request body while making any API call
 */
public final class InvalidRequestBodyException extends RuntimeException {

    public InvalidRequestBodyException(String message) {
        super(message);
    }
}
