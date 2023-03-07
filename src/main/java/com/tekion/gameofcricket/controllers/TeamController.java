package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.helper.NameRequestBody;
import com.tekion.gameofcricket.helper.CreateTeamRequestBody;
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

    @GetMapping("/byName")
    public Team getTeamByName(@RequestBody NameRequestBody requestBody) {
        LOGGER.info("GET call received : http://localhost:3004/teams/byName for \"" + requestBody.getName() + '\"');
        return teamService.getTeamByName(requestBody.getName());
    }

    @GetMapping("/{teamId}/players")
    public List<Player> getTeamPlayers(@PathVariable String teamId) {
        LOGGER.info("GET call received : http://localhost:3004/teams/" + teamId + "/players");
        return teamService.getTeamPlayers(new ObjectId(teamId));
    }

    @GetMapping("/byName/players")
    public List<Player> getTeamPlayers(@RequestBody NameRequestBody requestBody) {
        LOGGER.info(
                "GET call received : http://localhost:3004/teams/byName/players for \"" + requestBody.getName() + '\"');
        return teamService.getTeamPlayers(teamService.getTeamByName(requestBody.getName()).getId());
    }

    @PostMapping()
    public void addTeam(@RequestBody CreateTeamRequestBody requestBody) {
        LOGGER.info("POST call received : http://localhost:3004/teams");
        teamService.addTeam(requestBody);
    }
}
