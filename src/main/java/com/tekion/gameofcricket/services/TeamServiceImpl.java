package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.repositories.TeamRepository;
import com.tekion.gameofcricket.requestbody.CreateTeamRequestDto;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This is a concrete implementation for the TeamService interface
 */
@Service
@Lazy
public final class TeamServiceImpl implements TeamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceImpl.class);
    private final TeamRepository teamRepository;
    private final PlayerService playerService;

    public TeamServiceImpl(TeamRepository teamRepository, @Lazy PlayerService playerService) {
        this.teamRepository = teamRepository;
        this.playerService = playerService;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(ObjectId id) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isEmpty()) {
            throw new NoSuchElementException("No team available with id = " + id);
        }
        return team.get();
    }

    @Override
    public Team getTeamByName(String teamName) {
        Team team = teamRepository.getTeamByTeamNameEqualsIgnoreCase(teamName);
        if (team == null) {
            throw new NoSuchElementException("No team available with name = " + teamName);
        }
        return team;
    }

    @Override
    public List<Player> getTeamPlayers(ObjectId id) {
        List<ObjectId> playerIds = getTeamById(id).getPlayerIds();
        List<Player> players = new ArrayList<>();
        playerIds.forEach(playerId -> players.add(playerService.getPlayerById(playerId)));
        return players;
    }

    @Override
    public void addTeam(CreateTeamRequestDto requestBody) {
        List<ObjectId> playerIds = requestBody.getPlayerNames().stream()
                                              .map(playerName -> playerService.getPlayerByName(playerName).getId())
                                              .collect(Collectors.toList());
        Team team = new Team(requestBody.getTeamName(), playerIds);
        teamRepository.save(team);
        LOGGER.info("New team created : " + team);
    }

    @Override
    public void updateTeam(Team team) {
        teamRepository.save(team);
    }
}
