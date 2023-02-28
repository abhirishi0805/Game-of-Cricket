package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping("/matches")
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @RequestMapping("/matches/{id}")
    public Match getMatchById(@PathVariable String id) {
        return matchService.getMatchById(id);
    }

    @RequestMapping("/matches/date/{date}")
    public List<Match> getMatchByDate(@PathVariable String date) {
        return matchService.getMatchByDate(date);
    }

    @RequestMapping("/matches/team/{teamId}")
    public List<Match> getMatchByTeam(@PathVariable String teamId) {
        return matchService.getMatchByTeam(teamId);
    }
}
