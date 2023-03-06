package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.repositories.MatchRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(MatchServiceImpl.class);

    @Override
    public void addMatch(Match match) {
        matchRepository.save(match);
    }

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchById(ObjectId id) {
        return matchRepository.findById(id).orElse(null);
    }

    @Override
    public List<Match> getMatchByDate(String date) {
        return matchRepository.findMatchesByMatchDate(date);
    }

    @Override
    public List<Match> getMatchByTeam(ObjectId teamId) {
        return matchRepository.findMatchesByTeam1IdOrTeam2Id(teamId, teamId);
    }
}
