package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.repositories.MatchRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchById(String id) {
        return matchRepository.findById(new ObjectId(id)).orElse(null);
    }

    @Override
    public List<Match> getMatchByDate(String date) {
        return matchRepository.findMatchesByMatchDate(new Date(date));
    }

    @Override
    public List<Match> getMatchByTeam(String teamId) {
        return matchRepository.findMatchesByTeam1IdOrTeam2Id(new ObjectId(teamId));
    }
}
