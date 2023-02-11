package com.tekion.gameofcricket.models;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private int teamId;
    private String teamName;
    private List<Integer> playerIds;
    private int gamesWon, gamesLost, gamesDrawn;
    private int points;

    // to restrict instantiation without field data
    private Team() {
    }

    public Team(int teamId, String teamName, List<Integer> playerIds, int gamesWon, int gamesLost, int gamesDrawn,
                int points) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.playerIds = playerIds;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.gamesDrawn = gamesDrawn;
        this.points = points;
    }

    @Override
    public String toString() {
        return "Team {" + "\n\tteamId = " + teamId + ", teamName = '" + teamName + '\'' + ",\n\tplayerIds = " +
               playerIds + ",\n\tgamesWon = " + gamesWon + ", gamesLost = " + gamesLost + ", gamesDrawn = " +
               gamesDrawn + ", " + "points = " + points + "\n}";
    }

    public int getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public int getGamesDrawn() {
        return gamesDrawn;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public void setGamesDrawn(int gamesDrawn) {
        this.gamesDrawn = gamesDrawn;
    }

    public List<Integer> getPlayerIds() {
        return playerIds;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
