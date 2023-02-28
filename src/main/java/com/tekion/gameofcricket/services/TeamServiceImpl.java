package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.repositories.TeamRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements  TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void addTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(String id) {
        return teamRepository.findById(new ObjectId(id)).orElse(null);
    }
}
