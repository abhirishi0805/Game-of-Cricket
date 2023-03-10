package com.tekion.gameofcricket.utility.requestbody;

/**
 * This represents the body that client needs to pass while requesting for a team by its name
 */
public final class TeamRequestBody {

    private String teamName;

    public TeamRequestBody() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
