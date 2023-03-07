package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Match;
import org.bson.types.ObjectId;

import java.util.List;

public interface MatchService {

    List<Match> getAllMatches();

    Match getMatchById(ObjectId id);

    List<Match> getMatchByTeam(ObjectId teamId);

    List<Match> getMatchByDate(String date);

    void addMatch(Match match);
}