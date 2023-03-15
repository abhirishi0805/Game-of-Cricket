package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.responsebody.PlayerResponseDto;
import com.tekion.gameofcricket.responsebody.TeamResponseDto;
import com.tekion.gameofcricket.services.ResponseMappingService;
import com.tekion.gameofcricket.services.TeamService;
import com.tekion.gameofcricket.responsebody.GenericResponseDto;
import com.tekion.gameofcricket.utility.enums.ResponseStatus;
import com.tekion.gameofcricket.utility.InputVerifier;
import com.tekion.gameofcricket.requestbody.CreateTeamRequestDto;
import com.tekion.gameofcricket.requestbody.TeamRequestDto;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the controller to handle team related API requests
 */
@RestController
@RequestMapping("/teams")
public final class TeamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);
    @Autowired
    private TeamService teamService;
    @Autowired
    private ResponseMappingService responseMappingService;

    @GetMapping()
    public ResponseEntity<List<TeamResponseDto>> getAllTeams() {
        LOGGER.info("GET call received : http://localhost:3004/teams");
        List<Team> result = teamService.getAllTeams();
        return ResponseEntity.ok(
                result.stream().map(responseMappingService::mapTeam).collect(Collectors.toList()));
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponseDto> getTeamById(@PathVariable String teamId) {
        LOGGER.info("GET call received : http://localhost:3004/teams/" + teamId);
        InputVerifier.validateTeamId(teamId);
        Team result = teamService.getTeamById(new ObjectId(teamId));
        return ResponseEntity.ok(responseMappingService.mapTeam(result));
    }

    @GetMapping("/team-name")
    public ResponseEntity<TeamResponseDto> getTeamByName(@RequestBody TeamRequestDto requestBody) {
        LOGGER.info("GET call received : http://localhost:3004/teams/byName for \"" + requestBody.getTeamName() + '\"');
        InputVerifier.validateTeamRequestBody(requestBody);
        Team result = teamService.getTeamByName(requestBody.getTeamName());
        return ResponseEntity.ok(responseMappingService.mapTeam(result));
    }

    @GetMapping("/{teamId}/players")
    public ResponseEntity<List<PlayerResponseDto>> getTeamPlayers(@PathVariable String teamId) {
        LOGGER.info("GET call received : http://localhost:3004/teams/" + teamId + "/players");
        InputVerifier.validateTeamId(teamId);
        List<Player> result = teamService.getTeamPlayers(new ObjectId(teamId));
        return ResponseEntity.ok(
                result.stream().map(responseMappingService::mapPlayer).collect(Collectors.toList()));
    }

    @GetMapping("/team-name/players")
    public ResponseEntity<List<PlayerResponseDto>> getTeamPlayers(@RequestBody TeamRequestDto requestBody) {
        LOGGER.info(
                "GET call received : http://localhost:3004/teams/byName/players for \"" + requestBody.getTeamName() +
                '\"');
        InputVerifier.validateTeamRequestBody(requestBody);
        ObjectId teamId = teamService.getTeamByName(requestBody.getTeamName()).getId();
        List<Player> result = teamService.getTeamPlayers(teamId);
        return ResponseEntity.ok(
                result.stream().map(responseMappingService::mapPlayer).collect(Collectors.toList()));
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
