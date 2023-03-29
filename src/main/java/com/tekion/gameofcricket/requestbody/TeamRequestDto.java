package com.tekion.gameofcricket.requestbody;

import lombok.Getter;

/**
 * This represents the body that client needs to pass while trying to fetch anything by team name
 */
@Getter
public final class TeamRequestDto {
    private String teamName;
}
