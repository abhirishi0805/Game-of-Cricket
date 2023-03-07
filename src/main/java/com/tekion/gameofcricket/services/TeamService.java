package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.helper.CreateTeamRequestBody;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import org.bson.types.ObjectId;

import java.util.List;

public interface TeamService {

    List<Team> getAllTeams();

    Team getTeamById(ObjectId id);

    Team getTeamByName(String teamName);

    List<Player> getTeamPlayers(ObjectId id);

    void addTeam(CreateTeamRequestBody requestBody);

    void updateTeam(Team team);
}
