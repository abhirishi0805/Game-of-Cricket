package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.POST, value = "/teams")
    public void addTeam(@RequestBody Team team) {
        teamService.addTeam(team);
    }

    @RequestMapping("/teams")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @RequestMapping("/teams/{id}")
    public Team getTeamById(@PathVariable String id) {
        return teamService.getTeamById(id);
    }
}
