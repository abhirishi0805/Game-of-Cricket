package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.responsebody.PlayMatchResponseDto;

/**
 * This functional interface declares method to play a match
 */
public interface PlayMatchService {

    /**
     * Starts a new match between the given teams and eventually invokes storing its data
     *
     * @param team1 first team to play
     * @param team2 second team to play
     * @return result of the match to be returned to the client
     */
    PlayMatchResponseDto playMatch(Team team1, Team team2);
}
