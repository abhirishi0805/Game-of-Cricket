package com.tekion.gameofcricket.utility.requestbody;

/**
 * This represents the body that client needs to pass while requesting for a match to be played
 */
public final class PlayMatchRequestBody {

    private String team1Name;
    private String team2Name;

    public PlayMatchRequestBody() {
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    @Override
    public String toString() {
        return "Body = {" + "team1=" + team1Name + ", team2=" + team2Name + '}';
    }
}
