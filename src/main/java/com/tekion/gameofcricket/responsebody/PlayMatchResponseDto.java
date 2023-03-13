package com.tekion.gameofcricket.responsebody;

import lombok.Builder;
import lombok.Getter;

/**
 * This represents the response body returned whenever client requests to play a match
 */
@Builder
@Getter
public final class PlayMatchResponseDto {

    private String firstInnings;
    private String secondInnings;
    private String result;

}
