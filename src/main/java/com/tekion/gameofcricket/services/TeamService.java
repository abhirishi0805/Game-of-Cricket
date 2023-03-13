package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.requestbody.CreateTeamRequestDto;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * This interface declares methods to perform team related operations
 */
public interface TeamService {

    List<Team> getAllTeams();

    Team getTeamById(ObjectId id);

    Team getTeamByName(String teamName);

    List<Player> getTeamPlayers(ObjectId id);

    void addTeam(CreateTeamRequestDto requestBody);

    void updateTeam(Team team);
}
