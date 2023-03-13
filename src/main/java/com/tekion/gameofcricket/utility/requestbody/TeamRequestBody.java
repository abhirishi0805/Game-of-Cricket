package com.tekion.gameofcricket.utility.requestbody;

import lombok.Getter;

/**
 * This represents the body that client needs to pass while requesting for a team by its name
 */
@Getter
public final class TeamRequestBody {

    private String teamName;
}
