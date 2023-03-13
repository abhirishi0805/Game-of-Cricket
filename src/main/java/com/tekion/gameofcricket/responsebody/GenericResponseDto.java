package com.tekion.gameofcricket.responsebody;

import com.tekion.gameofcricket.utility.enums.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This represents the response that is returned in case of exceptions or for all requests other than GET
 */
@Builder
@Getter
public final class GenericResponseDto {

    private ResponseStatus status;
    @Setter
    private String message;
}