package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.helper.PlayMatchRequestBody;
import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.services.MatchService;
import com.tekion.gameofcricket.utility.LogUtils;
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
        LogUtils.logApiCall(RequestMethod.GET, "/matches/");
        return matchService.getAllMatches();
    }

    @GetMapping("/{matchId}")
    public Match getMatchById(@PathVariable String matchId) {
        LogUtils.logApiCall(RequestMethod.GET, "/matches/" + matchId);
        return matchService.getMatchById(new ObjectId(matchId));
    }

    @GetMapping("/date/{date}")
    public List<Match> getMatchByDate(@PathVariable String date) {
        LogUtils.logApiCall(RequestMethod.GET, "/matches/date/" + date);
        return matchService.getMatchByDate(date);
    }

    @GetMapping("/team/{teamId}")
    public List<Match> getMatchByTeam(@PathVariable String teamId) {
        LogUtils.logApiCall(RequestMethod.GET, "/matches/team/" + teamId);
        return matchService.getMatchByTeam(new ObjectId(teamId));
    }

    @PostMapping("/play")
    public void playMatch(@RequestBody PlayMatchRequestBody requestBody) {
        LogUtils.logApiCall(RequestMethod.POST, "/matches/play");
        matchService.playMatch(requestBody.getTeam1Id(), requestBody.getTeam2Id());
    }
}
