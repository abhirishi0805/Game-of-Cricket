package com.tekion.gameofcricket.helper;

import java.util.List;

public class CreateTeamRequestBody {
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
