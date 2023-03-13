package com.tekion.gameofcricket.responsebody;

import lombok.Getter;

/**
 * This represents the response body returned whenever client fetches a match
 */
@Getter
public class MatchResponseDto {
    private String team1;
    private String team2;
    private String result;
    private String matchDate;

    public MatchResponseDto(String team1, String team2, String result, String matchDate) {
        this.team1 = team1;
        this.team2 = team2;
        this.result = result;
        this.matchDate = matchDate;
    }
}
