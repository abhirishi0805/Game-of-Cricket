package com.tekion.gameofcricket.others;

import java.util.ArrayList;
import java.util.List;

public class Innings {

    private List<Ball> ballList;
    private int runsScored;
    private int wicketsLost;

    public Innings() {
        ballList = new ArrayList<>();
        runsScored = 0;
        wicketsLost = 0;
    }

    public List<Ball> getBallList() {
        return ballList;
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
