package com.tekion.gameofcricket.models;

import java.util.List;

public class Player {

    private int playerId;
    private String playerName;
    private List<Integer> runsScoredByMatch, wicketsTakenByMatch;

    // to restrict instantiation without field data
    private Player() {
    }

    public Player(int playerId, String playerName, List<Integer> runsScoredByMatch,
                  List<Integer> wicketsTakenByMatch) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.runsScoredByMatch = runsScoredByMatch;
        this.wicketsTakenByMatch = wicketsTakenByMatch;
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
