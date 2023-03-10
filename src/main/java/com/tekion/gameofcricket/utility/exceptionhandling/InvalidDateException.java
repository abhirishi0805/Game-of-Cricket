package com.tekion.gameofcricket.utility.exceptionhandling;

/**
 * This is a user-defined exception for date related anomalies
 */
public final class InvalidDateException extends RuntimeException {

    public InvalidDateException(String message) {
        super(message);
    }
}
