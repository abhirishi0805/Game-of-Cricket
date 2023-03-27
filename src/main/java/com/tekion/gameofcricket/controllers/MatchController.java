package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.responsebody.MatchResponseDto;
import com.tekion.gameofcricket.services.MatchService;
import com.tekion.gameofcricket.services.PlayMatchService;
import com.tekion.gameofcricket.services.TeamService;
import com.tekion.gameofcricket.services.ResponseMappingServiceImpl;
import com.tekion.gameofcricket.utility.InputVerifier;
import com.tekion.gameofcricket.requestbody.PlayMatchRequestDto;
import com.tekion.gameofcricket.requestbody.TeamRequestDto;
import com.tekion.gameofcricket.responsebody.PlayMatchResponseDto;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the controller to handle match related API requests
 */
@RestController
@RequestMapping("/matches")
public final class MatchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

    @Autowired
    private MatchService matchService;
    @Autowired
    @Lazy
    private PlayMatchService playMatchService;
    @Autowired
    @Lazy
    private TeamService teamService;
    @Autowired
    private ResponseMappingServiceImpl responseMappingService;

    @GetMapping()
    public ResponseEntity<List<MatchResponseDto>> getAllMatches() {
        LOGGER.info("GET call received : http://localhost:3004/matches");
        List<Match> result = matchService.getAllMatches();
        return ResponseEntity.ok(
                result.stream().map(responseMappingService::mapMatch).collect(Collectors.toUnmodifiableList()));
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<MatchResponseDto> getMatchById(@PathVariable String matchId) {
        LOGGER.info("GET call received : http://localhost:3004/matches/" + matchId);
        InputVerifier.validateMatchId(matchId);
        Match result = matchService.getMatchById(new ObjectId(matchId));
        return ResponseEntity.ok(responseMappingService.mapMatch(result));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<MatchResponseDto>> getMatchByTeamId(@PathVariable String teamId) {
        LOGGER.info("GET call received : http://localhost:3004/matches/team/" + teamId);
        InputVerifier.validateTeamId(teamId);
        List<Match> result = matchService.getMatchByTeam(new ObjectId(teamId));
        return ResponseEntity.ok(
                result.stream().map(responseMappingService::mapMatch).collect(Collectors.toUnmodifiableList()));
    }

    @GetMapping("/team/team-name")
    public ResponseEntity<List<MatchResponseDto>> getMatchByTeamName(@RequestBody TeamRequestDto requestBody) {
        LOGGER.info("GET call received : http://localhost:3004/matches/team/team-name for \"" + requestBody.getTeamName() +
                    '\"');
        InputVerifier.validateTeamRequestBody(requestBody);
        ObjectId teamId = teamService.getTeamByName(requestBody.getTeamName()).getId();
        List<Match> result = matchService.getMatchByTeam(teamId);
        return ResponseEntity.ok(
                result.stream().map(responseMappingService::mapMatch).collect(Collectors.toUnmodifiableList()));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<MatchResponseDto>> getMatchByDate(@PathVariable String date) {
        LOGGER.info("GET call received : http://localhost:3004/matches/date/" + date);
        InputVerifier.validateDate(date);
        List<Match> result = matchService.getMatchByDate(date);
        return ResponseEntity.ok(
                result.stream().map(responseMappingService::mapMatch).collect(Collectors.toUnmodifiableList()));
    }

    @PostMapping("/play")
    public ResponseEntity<PlayMatchResponseDto> playMatch(@RequestBody PlayMatchRequestDto requestBody) {
        LOGGER.info("POST call received : http://localhost:3004/matches/play");
        InputVerifier.validatePlayMatchRequestBody(requestBody);
        Team team1 = teamService.getTeamByName(requestBody.getTeam1Name());
        Team team2 = teamService.getTeamByName(requestBody.getTeam2Name());
        return ResponseEntity.ok(playMatchService.playMatch(team1, team2));
    }
}
