package Models;

import java.util.ArrayList;

public class Match {
    public static final int MATCH_LENGTH_IN_OVERS = 6;
    private Team teamA, teamB;

    // first innings --> team A bats, team B bowls
    // second innings --> team A bowls, team B bats
    private Innings firstInnings, secondInnings;

    public Match(Team teamA, Team teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
        firstInnings = new Innings();
        secondInnings = new Innings();
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
