package com.tekion.gameofcricket.responsebody;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This represents the response body returned whenever client fetches a match
 */
@AllArgsConstructor
@Getter
public class MatchResponseDto {
    private String team1;
    private String team2;
    private String result;
    private String matchDate;
}
