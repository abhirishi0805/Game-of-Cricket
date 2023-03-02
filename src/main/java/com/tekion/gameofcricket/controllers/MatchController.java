package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.helper.PlayMatchRequestBody;
import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.services.MatchService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/")
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{matchId}")
    public Match getMatchById(@PathVariable String matchId) {
        return matchService.getMatchById(new ObjectId(matchId));
    }

    @GetMapping("/date/{date}")
    public List<Match> getMatchByDate(@PathVariable String date) {
        return matchService.getMatchByDate(date);
    }

    @GetMapping("/team/{teamId}")
    public List<Match> getMatchByTeam(@PathVariable String teamId) {
        return matchService.getMatchByTeam(new ObjectId(teamId));
    }

    @PostMapping("/play")
    public void playMatch(@RequestBody PlayMatchRequestBody playMatchRequestBody) {
        matchService.playMatch(playMatchRequestBody.getTeam1Id(), playMatchRequestBody.getTeam2Id());
    }
}
