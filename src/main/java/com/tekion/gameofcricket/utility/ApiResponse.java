package com.tekion.gameofcricket.utility;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * This represents the response that is returned in case of exceptions or for all requests other than GET
 */
@Builder
public final class ApiResponse {

    @Getter
    private ResponseStatus status;
    @Getter
    @Setter
    private String message;
}