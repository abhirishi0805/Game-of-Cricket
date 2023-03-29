package com.tekion.gameofcricket.responsebody;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * This represents the response body returned whenever client fetches a team
 */
@AllArgsConstructor
@Getter
public class TeamResponseDto {
    private String teamName;
    private List<String> players;
    private int gamesWon;
    private int gamesDrawn;
    private int gamesLost;
}
