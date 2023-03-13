package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.services.TeamService;
import com.tekion.gameofcricket.responsebody.GenericResponseDto;
import com.tekion.gameofcricket.utility.enums.ResponseStatus;
import com.tekion.gameofcricket.utility.exceptionhandling.InputVerifier;
import com.tekion.gameofcricket.requestbody.CreateTeamRequestDto;
import com.tekion.gameofcricket.requestbody.TeamRequestDto;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the controller to handle team related API requests
 */
@RestController
@RequestMapping("/teams")
public final class TeamController {

    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);
    @Autowired
    private TeamService teamService;

    @GetMapping()
    public ResponseEntity<List<Team>> getAllTeams() {
        LOGGER.info("GET call received : http://localhost:3004/teams");
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable String teamId) {
        LOGGER.info("GET call received : http://localhost:3004/teams/" + teamId);
        InputVerifier.validateTeamId(teamId);
        return ResponseEntity.ok(teamService.getTeamById(new ObjectId(teamId)));
    }

    @GetMapping("/byName")
    public ResponseEntity<Team> getTeamByName(@RequestBody TeamRequestDto requestBody) {
        LOGGER.info("GET call received : http://localhost:3004/teams/byName for \"" + requestBody.getTeamName() + '\"');
        InputVerifier.validateTeamRequestBody(requestBody);
        return ResponseEntity.ok(teamService.getTeamByName(requestBody.getTeamName()));
    }

    @GetMapping("/{teamId}/players")
    public ResponseEntity<List<Player>> getTeamPlayers(@PathVariable String teamId) {
        LOGGER.info("GET call received : http://localhost:3004/teams/" + teamId + "/players");
        InputVerifier.validateTeamId(teamId);
        return ResponseEntity.ok(teamService.getTeamPlayers(new ObjectId(teamId)));
    }

    @GetMapping("/byName/players")
    public ResponseEntity<List<Player>> getTeamPlayers(@RequestBody TeamRequestDto requestBody) {
        LOGGER.info(
                "GET call received : http://localhost:3004/teams/byName/players for \"" + requestBody.getTeamName() +
                '\"');
        InputVerifier.validateTeamRequestBody(requestBody);
        ObjectId teamId = teamService.getTeamByName(requestBody.getTeamName()).getId();
        return ResponseEntity.ok(teamService.getTeamPlayers(teamId));
    }

    @PostMapping()
    public ResponseEntity<GenericResponseDto> addTeam(@RequestBody CreateTeamRequestDto requestBody) {
        LOGGER.info("POST call received : http://localhost:3004/teams");
        InputVerifier.validateCreateTeamRequestBody(requestBody);
        teamService.addTeam(requestBody);
        GenericResponseDto response = GenericResponseDto.builder().status(ResponseStatus.SUCCESS)
                                                        .message("Team successfully " + "created").build();
        return ResponseEntity.ok(response);
    }
}
