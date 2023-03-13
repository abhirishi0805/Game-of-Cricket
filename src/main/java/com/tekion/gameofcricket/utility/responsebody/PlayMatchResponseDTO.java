package com.tekion.gameofcricket.utility.responsebody;

import lombok.Builder;
import lombok.Getter;

/**
 * This represents the response body returned whenever client requests to play a match
 */
@Builder
public final class PlayMatchResponseDTO {

    @Getter
    private String firstInnings;
    @Getter
    private String secondInnings;
    @Getter
    private String result;

}
