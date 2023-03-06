package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.services.TeamService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;

    @PostMapping()
    public void addTeam(@RequestBody Team team) {
        LOGGER.info("POST call received : http://localhost:3004/teams");
        teamService.addTeam(team);
    }

    @GetMapping()
    public List<Team> getAllTeams() {
        LOGGER.info("GET call received : http://localhost:3004/teams");
        return teamService.getAllTeams();
    }

    @GetMapping("/{teamId}")
    public Team getTeamById(@PathVariable String teamId) {
        LOGGER.info("GET call received : http://localhost:3004/teams/" + teamId);
        return teamService.getTeamById(new ObjectId(teamId));
    }

    @GetMapping("/{teamId}/players")
    public List<Player> getTeamPlayers(@PathVariable String teamId) {
        LOGGER.info("GET call received : http://localhost:3004/teams/" + teamId + "/players");
        return teamService.getTeamPlayers(new ObjectId(teamId));
    }
}
