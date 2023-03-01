package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.utility.MatchResult;
import org.bson.types.ObjectId;

import java.util.List;

public interface TeamService {

    void addTeam(Team team);

    List<Team> getAllTeams();

    Team getTeamById(ObjectId id);

    List<Player> getTeamPlayers(ObjectId id);

    void updateTeamDataPostMatch(Team team1, Team team2, MatchResult result);
}
