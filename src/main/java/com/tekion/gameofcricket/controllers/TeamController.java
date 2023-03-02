package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.services.TeamService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/")
    public void addTeam(@RequestBody Team team) {
        teamService.addTeam(team);
    }

    @GetMapping("/")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{teamId}")
    public Team getTeamById(@PathVariable String teamId) {
        return teamService.getTeamById(new ObjectId(teamId));
    }

    @GetMapping("/{teamId}/players")
    public List<Player> getTeamPlayers(@PathVariable String teamId) {
        return teamService.getTeamPlayers(new ObjectId(teamId));
    }
}
