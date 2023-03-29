package com.tekion.gameofcricket.requestbody;

import lombok.Getter;

/**
 * This represents the body that client needs to pass while trying to fetch anything by player name
 */
@Getter
public final class PlayerRequestDto {
    private String playerName;
}
