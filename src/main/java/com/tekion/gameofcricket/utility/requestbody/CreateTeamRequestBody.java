package com.tekion.gameofcricket.utility.requestbody;

import java.util.List;

/**
 * This represents the body that client needs to pass while requesting to create a new team
 */
public final class CreateTeamRequestBody {

    private String teamName;
    List<String> playerNames;

    public CreateTeamRequestBody() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(List<String> playerNames) {
        this.playerNames = playerNames;
    }
}
