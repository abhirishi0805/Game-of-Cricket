package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.utility.requestbody.NameRequestBody;
import com.tekion.gameofcricket.utility.requestbody.PlayMatchRequestBody;
import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.services.MatchService;
import com.tekion.gameofcricket.services.PlayMatchService;
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
@RequestMapping("/matches")
public class MatchController {

    @Lazy
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
    @Lazy
    private ApplicationContext applicationContext;

    @GetMapping()
    public ResponseEntity<List<Match>> getAllMatches() {
        LOGGER.info("GET call received : http://localhost:3004/matches");
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<Match> getMatchById(@PathVariable String matchId) {
        LOGGER.info("GET call received : http://localhost:3004/matches/" + matchId);
        InputVerifier.verifyMatchId(matchId);
        return ResponseEntity.ok(matchService.getMatchById(new ObjectId(matchId)));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Match>> getMatchByTeamId(@PathVariable String teamId) {
        LOGGER.info("GET call received : http://localhost:3004/matches/team/" + teamId);
        InputVerifier.verifyTeamId(teamId);
        return ResponseEntity.ok(matchService.getMatchByTeam(new ObjectId(teamId)));
    }

    @GetMapping("/team/byName")
    public ResponseEntity<List<Match>> getMatchByTeamName(@RequestBody NameRequestBody requestBody) {
        LOGGER.info(
                "GET call received : http://localhost:3004/matches/team/byName for \"" + requestBody.getName() + '\"');
        ObjectId teamId = teamService.getTeamByName(requestBody.getName()).getId();
        return ResponseEntity.ok(matchService.getMatchByTeam(teamId));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Match>> getMatchByDate(@PathVariable String date) {
        LOGGER.info("GET call received : http://localhost:3004/matches/date/" + date);
        return ResponseEntity.ok(matchService.getMatchByDate(date));
    }

    @PostMapping("/play")
    public ResponseEntity<ApiResponse> playMatch(@RequestBody PlayMatchRequestBody requestBody) {
        LOGGER.info("POST call received : http://localhost:3004/matches/play");
        Team team1 = teamService.getTeamByName(requestBody.getTeam1Name());
        Team team2 = teamService.getTeamByName(requestBody.getTeam2Name());
        playMatchService.playMatch(team1, team2);
        ApiResponse response = applicationContext.getBean(ApiResponse.class);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("Match successfully played");
        return ResponseEntity.ok(response);
    }
}
