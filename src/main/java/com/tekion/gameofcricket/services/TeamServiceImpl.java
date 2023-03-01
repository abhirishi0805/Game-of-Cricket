package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.repositories.TeamRepository;
import com.tekion.gameofcricket.utility.MatchResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerService playerService;

    @Override
    public void addTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(ObjectId id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Override
    public List<Player> getTeamPlayers(ObjectId id) {
        List<ObjectId> playerIds = getTeamById(id).getPlayerIds();
        List<Player> players = new ArrayList<>();
        playerIds.forEach(playerId -> players.add(playerService.getPlayerById(playerId)));
        return players;
    }

    @Override
    public void updateTeamDataPostMatch(Team team1, Team team2, MatchResult result) {
        if (result == MatchResult.TEAM_1_WON) {
            team1.setGamesWon(team1.getGamesWon() + 1);
            team2.setGamesLost(team2.getGamesLost() + 1);
        } else if (result == MatchResult.TEAM_2_WON) {
            team1.setGamesLost(team1.getGamesLost() + 1);
            team2.setGamesWon(team2.getGamesWon() + 1);
        } else {
            team1.setGamesDrawn(team1.getGamesDrawn() + 1);
            team2.setGamesDrawn(team2.getGamesDrawn() + 1);
        }
        addTeam(team1);
        addTeam(team2);
    }
}
