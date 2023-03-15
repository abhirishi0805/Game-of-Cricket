package com.tekion.gameofcricket.responsebody;

import lombok.Builder;
import lombok.Getter;

/**
 * This represents the response body returned whenever client fetches a match
 */
@Builder
@Getter
public class MatchResponseDto {

    private String team1;
    private String team2;
    private String result;
    private String matchDate;
}
