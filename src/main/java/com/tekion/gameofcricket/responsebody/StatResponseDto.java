package com.tekion.gameofcricket.responsebody;

import lombok.Builder;
import lombok.Getter;

/**
 * This represents the response body returned whenever client fetches player stat (match-wise)
 */
@Builder
@Getter
public class StatResponseDto {
    private String team;
    private String opponent;
    private int runsScored;
    private int ballsFaced;
    private int sixesHit;
    private int foursHit;
    private int ballsThrown;
    private int runsConceded;
    private int wicketsTaken;
}
