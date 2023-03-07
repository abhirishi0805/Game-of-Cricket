package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.helper.CreateTeamRequestBody;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.repositories.TeamRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceImpl.class);
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    @Lazy
    private PlayerService playerService;
    @Autowired
    @Lazy
    private ApplicationContext applicationContext;

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(ObjectId id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Override
    public Team getTeamByName(String teamName) {
        return teamRepository.getTeamByTeamName(teamName);
    }

    @Override
    public List<Player> getTeamPlayers(ObjectId id) {
        List<ObjectId> playerIds = getTeamById(id).getPlayerIds();
        List<Player> players = new ArrayList<>();
        playerIds.forEach(playerId -> players.add(playerService.getPlayerById(playerId)));
        return players;
    }

    @Override
    public void addTeam(CreateTeamRequestBody requestBody) {
        Team team = applicationContext.getBean(Team.class);
        team.setTeamName(requestBody.getTeamName());
        List<ObjectId> playerIds = requestBody.getPlayerNames().stream()
                                              .map(playerName -> playerService.getPlayerByName(playerName).getId())
                                              .collect(Collectors.toList());
        team.setPlayerIds(playerIds);
        teamRepository.save(team);
        LOGGER.info("New team created : " + team);
    }

    @Override
    public void updateTeam(Team team) {
        teamRepository.save(team);
    }
}
