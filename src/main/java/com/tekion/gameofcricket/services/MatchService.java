package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Match;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * This interface declares methods to perform match related operations (except starting a new match)
 */
public interface MatchService {

    List<Match> getAllMatches();

    Match getMatchById(ObjectId id);

    List<Match> getMatchByTeam(ObjectId teamId);

    List<Match> getMatchByDate(String date);

    void addMatch(Match match);
}