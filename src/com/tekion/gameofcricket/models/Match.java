package com.tekion.gameofcricket.models;

import com.tekion.gameofcricket.others.Innings;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private Team teamA, teamB;

    // first innings --> team A bats, team B bowls
    // second innings --> team A bowls, team B bats
    private Innings firstInnings, secondInnings;

    // to restrict instantiation without field data
    private Match() {
    }

    public Match(Team teamA, Team teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
        firstInnings = new Innings();
        secondInnings = new Innings();
    }

    @Override
    public String toString() {
        return "Match {" + "\n\tteamA = " + teamA.getTeamName() + " (id = " + teamA.getTeamId() + "),\n\tteamB = " +
               teamB.getTeamName() + " (id = " + teamB.getTeamId() + "),\n\tfirstInnings" + " = " + firstInnings +
               ",\n\tsecondInnings = " + secondInnings + "\n}";
    }

    public Innings getFirstInnings() {
        return firstInnings;
    }

    public Innings getSecondInnings() {
        return secondInnings;
    }

    public Team getTeamA() {
        return teamA;
    }

    public Team getTeamB() {
        return teamB;
    }
}
