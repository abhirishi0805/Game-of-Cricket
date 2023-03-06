package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.helper.PlayMatchRequestBody;
import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.services.MatchService;
import com.tekion.gameofcricket.services.PlayMatchService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @GetMapping()
    public List<Match> getAllMatches() {
        LOGGER.info("GET call received : http://localhost:3004/matches");
        return matchService.getAllMatches();
    }

    @GetMapping("/{matchId}")
    public Match getMatchById(@PathVariable String matchId) {
        LOGGER.info("GET call received : http://localhost:3004/matches/" + matchId);
        return matchService.getMatchById(new ObjectId(matchId));
    }

    @GetMapping("/date/{date}")
    public List<Match> getMatchByDate(@PathVariable String date) {
        LOGGER.info("GET call received : http://localhost:3004/matches/date/" + date);
        return matchService.getMatchByDate(date);
    }

    @GetMapping("/team/{teamId}")
    public List<Match> getMatchByTeam(@PathVariable String teamId) {
        LOGGER.info("GET call received : http://localhost:3004/matches/team/" + teamId);
        return matchService.getMatchByTeam(new ObjectId(teamId));
    }

    @PostMapping("/play")
    public void playMatch(@RequestBody PlayMatchRequestBody requestBody) {
        LOGGER.info("POST call received : http://localhost:3004/matches/play");
        playMatchService.playMatch(requestBody.getTeam1Id(), requestBody.getTeam2Id());
    }
}
