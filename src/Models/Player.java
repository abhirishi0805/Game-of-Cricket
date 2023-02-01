package Models;

import java.util.ArrayList;

public class Player {
    private int playerID;
    private String playerName;
    private ArrayList<Integer> runsScoredByMatch, wicketsTakenByMatch;

    // to restrict instantiation without field data
    private Player() { }

    public Player(int playerID, String playerName, ArrayList<Integer> runsScoredByMatch, ArrayList<Integer> wicketsTakenByMatch) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.runsScoredByMatch = runsScoredByMatch;
        this.wicketsTakenByMatch = wicketsTakenByMatch;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Integer> getRunsScoredByMatch() {
        return runsScoredByMatch;
    }

    public ArrayList<Integer> getWicketsTakenByMatch() {
        return wicketsTakenByMatch;
    }
}
