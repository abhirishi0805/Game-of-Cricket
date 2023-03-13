package com.tekion.gameofcricket.responsebody;

import lombok.Getter;

/**
 * This represents the response body returned whenever client fetches a player
 */
@Getter
public final class PlayerResponseDto {

    private String playerName;
    private int totalRunsScored;
    private int totalWicketsTaken;
    private int gamesPlayed;

    public PlayerResponseDto(String playerName, int totalRunsScored, int totalWicketsTaken, int gamesPlayed) {
        this.playerName = playerName;
        this.totalRunsScored = totalRunsScored;
        this.totalWicketsTaken = totalWicketsTaken;
        this.gamesPlayed = gamesPlayed;
    }
}
