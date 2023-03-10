package com.tekion.gameofcricket.utility;

import org.springframework.stereotype.Component;

/**
 * This represents the response that is returned in case of exceptions or for all requests other than GET
 */
@Component
public final class ApiResponse {

    private ResponseStatus status;
    private String message;

    public ApiResponse() {
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
