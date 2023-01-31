package Models;

import java.util.ArrayList;

public class Innings {
    private ArrayList<Ball> ballList;
    private int runsScored;
    private int wicketsLost;

    public Innings() {
        ballList = new ArrayList<>();
        runsScored = 0;
        wicketsLost = 0;
    }

    public ArrayList<Ball> getBallList() {
        return ballList;
    }

    public void setBallList(ArrayList<Ball> ballList) {
        this.ballList = ballList;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getWicketsLost() {
        return wicketsLost;
    }

    public void setWicketsLost(int wicketsLost) {
        this.wicketsLost = wicketsLost;
    }
}
