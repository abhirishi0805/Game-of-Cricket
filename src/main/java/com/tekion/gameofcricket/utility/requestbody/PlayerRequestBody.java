package com.tekion.gameofcricket.utility.requestbody;

/**
 * This represents the body that client needs to pass while
 *           • requesting to create a new player
 *           • requesting for a player by its name
 */
public final class PlayerRequestBody {
    private String playerName;

    public PlayerRequestBody() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
