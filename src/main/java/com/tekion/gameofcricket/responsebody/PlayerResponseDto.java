package com.tekion.gameofcricket.responsebody;

import lombok.Builder;
import lombok.Getter;

/**
 * This represents the response body returned whenever client fetches a player
 */
@Builder
@Getter
public final class PlayerResponseDto {

    private String playerName;
    private int totalRunsScored;
    private int totalWicketsTaken;
    private int gamesPlayed;
}
