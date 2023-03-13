package com.tekion.gameofcricket.responsebody;

import lombok.Getter;

import java.util.List;

/**
 * This represents the response body returned whenever client fetches a team
 */
@Getter
public class TeamResponseDto {
    private String teamName;
    private List<String> players;
    private int gamesWon;
    private int gamesDrawn;
    private int gamesLost;

    public TeamResponseDto(String teamName, List<String> players, int gamesWon, int gamesDrawn, int gamesLost) {
        this.teamName = teamName;
        this.players = players;
        this.gamesWon = gamesWon;
        this.gamesDrawn = gamesDrawn;
        this.gamesLost = gamesLost;
    }
}
