package com.tekion.gameofcricket.utility.requestbody;

import lombok.Getter;

import java.util.List;

/**
 * This represents the body that client needs to pass while requesting to create a new team
 */
@Getter
public final class CreateTeamRequestBody {

    private String teamName;
    List<String> playerNames;
}
