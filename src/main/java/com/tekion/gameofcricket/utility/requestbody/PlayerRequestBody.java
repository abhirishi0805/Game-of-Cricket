package com.tekion.gameofcricket.utility.requestbody;

import lombok.Getter;

/**
 * This represents the body that client needs to pass while
 *                                   • requesting to create a new player
 *                                   • requesting for a player by its name
 */
@Getter
public final class PlayerRequestBody {

    private String playerName;
}
