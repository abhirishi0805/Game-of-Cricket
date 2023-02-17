package com.tekion.gameofcricket.models;

import java.util.List;

public class Player {

    private final int playerId;
    private final String playerName;
    private final List<Integer> runsScoredByMatch, wicketsTakenByMatch;

    public Player(int playerId, String playerName, List<Integer> runsScoredByMatch, List<Integer> wicketsTakenByMatch) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.runsScoredByMatch = runsScoredByMatch;
        this.wicketsTakenByMatch = wicketsTakenByMatch;
    }

    @Override
    public String toString() {
        return "Player {" + "\n\tplayerId = " + playerId + ",\n\tplayerName = '" + playerName + '\'' + "," +
               "\n\trunsScoredByMatch = " + runsScoredByMatch + ",\n\twicketsTakenByMatch = " + wicketsTakenByMatch +
               "\n}";
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Integer> getRunsScoredByMatch() {
        return runsScoredByMatch;
    }

    public List<Integer> getWicketsTakenByMatch() {
        return wicketsTakenByMatch;
    }
}
