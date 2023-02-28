package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Team;

import java.util.List;

public interface TeamService {
    void addTeam(Team team);
    List<Team> getAllTeams();
    Team getTeamById(String id);
}
