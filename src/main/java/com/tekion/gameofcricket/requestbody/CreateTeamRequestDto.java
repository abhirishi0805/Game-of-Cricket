package com.tekion.gameofcricket.requestbody;

import lombok.Getter;

import java.util.List;

/**
 * This represents the body that client needs to pass while requesting to create a new team
 */
@Getter
public final class CreateTeamRequestDto {

    private String teamName;
    List<String> playerNames;
}
