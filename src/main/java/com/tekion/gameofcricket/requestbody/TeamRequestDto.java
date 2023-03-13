package com.tekion.gameofcricket.requestbody;

import lombok.Getter;

/**
 * This represents the body that client needs to pass while requesting for a team by its name
 */
@Getter
public final class TeamRequestDto {

    private String teamName;
}
