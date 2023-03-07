package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.helper.CreateTeamRequestBody;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.repositories.TeamRepository;
import com.tekion.gameofcricket.utility.MatchResult;
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
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(ObjectId id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Override
    public void updateTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public List<Player> getTeamPlayers(ObjectId id) {
        return generateDeatiledTeamPlayerList(getTeamById(id));
    }

    @Override
    public Team getTeamByName(String teamName) {
        return teamRepository.getTeamByTeamName(teamName);
    }

    @Override
    public List<Player> getTeamPlayers(String teamName) {
        return generateDeatiledTeamPlayerList(getTeamByName(teamName));
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
        updateTeam(team1);
        updateTeam(team2);
    }

    private List<Player> generateDeatiledTeamPlayerList(Team team) {
        List<ObjectId> playerIds = team.getPlayerIds();
        List<Player> players = new ArrayList<>();
        playerIds.forEach(playerId -> players.add(playerService.getPlayerById(playerId)));
        return players;
    }
}
