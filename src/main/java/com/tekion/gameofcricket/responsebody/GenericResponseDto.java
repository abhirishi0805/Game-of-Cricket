package com.tekion.gameofcricket.responsebody;

import com.tekion.gameofcricket.utility.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This represents the response that is returned in case of exceptions or for all requests other than GET
 */
@AllArgsConstructor
@Getter
public final class GenericResponseDto {
    private ResponseStatus status;
    private String message;
}