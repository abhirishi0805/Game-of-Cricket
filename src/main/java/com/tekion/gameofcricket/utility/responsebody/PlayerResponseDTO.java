package com.tekion.gameofcricket.utility.responsebody;

import lombok.Getter;

/**
 * This represents the response body returned whenever client requests for a player
 */
@Getter
public final class PlayerResponseDTO {

    private String playerName;
    private int totalRunsScored;
    private int totalWicketsTaken;
    private int gamesPlayed;

    public PlayerResponseDTO(String playerName, int totalRunsScored, int totalWicketsTaken, int gamesPlayed) {
        this.playerName = playerName;
        this.totalRunsScored = totalRunsScored;
        this.totalWicketsTaken = totalWicketsTaken;
        this.gamesPlayed = gamesPlayed;
    }
}
