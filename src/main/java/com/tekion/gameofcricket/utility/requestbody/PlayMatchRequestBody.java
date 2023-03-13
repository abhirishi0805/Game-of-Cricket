package com.tekion.gameofcricket.utility.requestbody;

import lombok.Getter;

/**
 * This represents the body that client needs to pass while requesting for a match to be played
 */
@Getter
public final class PlayMatchRequestBody {

    private String team1Name;
    private String team2Name;

    @Override
    public String toString() {
        return "Body = {" + "team1=" + team1Name + ", team2=" + team2Name + '}';
    }
}
