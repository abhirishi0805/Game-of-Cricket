package com.tekion.gameofcricket.utility;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This represents the response that is returned in case of exceptions or for all requests other than GET
 */
@Builder
@Getter
public final class ApiResponse {

    private ResponseStatus status;
    @Setter
    private String message;
}