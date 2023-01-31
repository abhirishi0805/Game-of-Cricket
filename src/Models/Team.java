package Models;

import java.util.ArrayList;

public class Team {
    public final static int TEAM_SIZE = 3;
    private int teamID;
    private String teamName;
    private ArrayList<Integer> playerIDs;
    private int gamesWon, gamesLost, gamesDrawn;

    // to restrict instantiation without field data
    private Team() {}

    public Team(int teamID, String teamName, ArrayList<Integer> playerIDs, int gamesWon, int gamesLost, int gamesDrawn) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.playerIDs = playerIDs;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.gamesDrawn = gamesDrawn;
    }

    public int getTeamID() {
        return teamID;
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

    public ArrayList<Integer> getPlayerIDs() {
        return playerIDs;
    }
}
