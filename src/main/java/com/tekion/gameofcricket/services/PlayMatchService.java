package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.responsebody.PlayMatchResponseDto;

/**
 * This functional interface declares method to play a match
 */
public interface PlayMatchService {

    PlayMatchResponseDto playMatch(Team team1, Team team2);
}
