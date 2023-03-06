package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.services.TeamService;
import com.tekion.gameofcricket.utility.LogUtils;
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
        LogUtils.logApiCall(RequestMethod.POST, "/teams/");
        teamService.addTeam(team);
    }

    @GetMapping("/")
    public List<Team> getAllTeams() {
        LogUtils.logApiCall(RequestMethod.GET, "/teams/");
        return teamService.getAllTeams();
    }

    @GetMapping("/{teamId}")
    public Team getTeamById(@PathVariable String teamId) {
        LogUtils.logApiCall(RequestMethod.GET, "/teams/" + teamId);
        return teamService.getTeamById(new ObjectId(teamId));
    }

    @GetMapping("/{teamId}/players")
    public List<Player> getTeamPlayers(@PathVariable String teamId) {
        LogUtils.logApiCall(RequestMethod.GET, "/teams/" + teamId + "/players");
        return teamService.getTeamPlayers(new ObjectId(teamId));
    }
}
