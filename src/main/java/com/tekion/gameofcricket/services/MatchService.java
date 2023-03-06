package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Match;
import org.bson.types.ObjectId;

import java.util.List;

public interface MatchService {

    void addMatch(Match match);

    List<Match> getAllMatches();

    Match getMatchById(ObjectId id);

    List<Match> getMatchByDate(String date);

    List<Match> getMatchByTeam(ObjectId teamId);
}
