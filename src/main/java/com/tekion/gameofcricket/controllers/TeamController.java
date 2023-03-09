package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.utility.requestbody.CreateTeamRequestBody;
import com.tekion.gameofcricket.utility.requestbody.NameRequestBody;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.services.TeamService;
import com.tekion.gameofcricket.utility.ApiResponse;
import com.tekion.gameofcricket.utility.exceptionhandling.InputVerifier;
import com.tekion.gameofcricket.utility.ResponseStatus;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);
    @Autowired
    private TeamService teamService;
    @Autowired
    @Lazy
    private ApplicationContext applicationContext;

    @GetMapping()
    public ResponseEntity<List<Team>> getAllTeams() {
        LOGGER.info("GET call received : http://localhost:3004/teams");
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable String teamId) {
        LOGGER.info("GET call received : http://localhost:3004/teams/" + teamId);
        InputVerifier.verifyTeamId(teamId);
        return ResponseEntity.ok(teamService.getTeamById(new ObjectId(teamId)));
    }

    @GetMapping("/byName")
    public ResponseEntity<Team> getTeamByName(@RequestBody NameRequestBody requestBody) {
        LOGGER.info("GET call received : http://localhost:3004/teams/byName for \"" + requestBody.getName() + '\"');
        return ResponseEntity.ok(teamService.getTeamByName(requestBody.getName()));
    }

    @GetMapping("/{teamId}/players")
    public ResponseEntity<List<Player>> getTeamPlayers(@PathVariable String teamId) {
        LOGGER.info("GET call received : http://localhost:3004/teams/" + teamId + "/players");
        InputVerifier.verifyTeamId(teamId);
        return ResponseEntity.ok(teamService.getTeamPlayers(new ObjectId(teamId)));
    }

    @GetMapping("/byName/players")
    public ResponseEntity<List<Player>> getTeamPlayers(@RequestBody NameRequestBody requestBody) {
        LOGGER.info(
                "GET call received : http://localhost:3004/teams/byName/players for \"" + requestBody.getName() + '\"');
        ObjectId teamId = teamService.getTeamByName(requestBody.getName()).getId();
        return ResponseEntity.ok(teamService.getTeamPlayers(teamId));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> addTeam(@RequestBody CreateTeamRequestBody requestBody) {
        LOGGER.info("POST call received : http://localhost:3004/teams");
        teamService.addTeam(requestBody);
        ApiResponse response = applicationContext.getBean(ApiResponse.class);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("Team successfully created");
        return ResponseEntity.ok(response);
    }
}
