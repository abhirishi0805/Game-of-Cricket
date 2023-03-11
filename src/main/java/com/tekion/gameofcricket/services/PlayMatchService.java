package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.utility.responsebody.PlayMatchResponseDTO;

/**
 * This functional interface declares method to play a match
 */
public interface PlayMatchService {

    PlayMatchResponseDTO playMatch(Team team1, Team team2);
}
