package Models;

public class Match {
    private Team teamA, teamB;

    // first innings --> team A bats, team B bowls
    // second innings --> team A bowls, team B bats
    private Innings firstInnings, secondInnings;

    // to restrict instantiation without field data
    private Match() { }

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
