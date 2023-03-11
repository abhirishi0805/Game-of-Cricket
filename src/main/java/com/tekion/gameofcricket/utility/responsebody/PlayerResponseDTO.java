package com.tekion.gameofcricket.utility.responsebody;

public final class PlayerResponseDTO {
    private String playerName;
    private int totalRunsScored = 0;
    private int totalWicketsTaken = 0;
    private int gamesPlayed = 0;

    public PlayerResponseDTO(String playerName, int totalRunsScored, int totalWicketsTaken, int gamesPlayed) {
        this.playerName = playerName;
        this.totalRunsScored = totalRunsScored;
        this.totalWicketsTaken = totalWicketsTaken;
        this.gamesPlayed = gamesPlayed;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getTotalRunsScored() {
        return totalRunsScored;
    }

    public void setTotalRunsScored(int totalRunsScored) {
        this.totalRunsScored = totalRunsScored;
    }

    public int getTotalWicketsTaken() {
        return totalWicketsTaken;
    }

    public void setTotalWicketsTaken(int totalWicketsTaken) {
        this.totalWicketsTaken = totalWicketsTaken;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
}
