package com.tekion.gameofcricket.utility.responsebody;

import lombok.Getter;

public final class PlayerResponseDTO {

    @Getter
    private String playerName;
    @Getter
    private int totalRunsScored;
    @Getter
    private int totalWicketsTaken;
    @Getter
    private int gamesPlayed;

    public PlayerResponseDTO(String playerName, int totalRunsScored, int totalWicketsTaken, int gamesPlayed) {
        this.playerName = playerName;
        this.totalRunsScored = totalRunsScored;
        this.totalWicketsTaken = totalWicketsTaken;
        this.gamesPlayed = gamesPlayed;
    }
}
