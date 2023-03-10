package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Team;

/**
 * This functional interface declares method to play a match
 */
public interface PlayMatchService {

    void playMatch(Team team1, Team team2);
}
