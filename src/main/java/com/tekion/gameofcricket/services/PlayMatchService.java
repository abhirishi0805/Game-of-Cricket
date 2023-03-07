package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Team;
import org.bson.types.ObjectId;

public interface PlayMatchService {

    void playMatch(Team team1, Team team2);
}
