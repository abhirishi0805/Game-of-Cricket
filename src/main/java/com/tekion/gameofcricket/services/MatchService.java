package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Match;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public interface MatchService {

    void addMatch(Match match);

    List<Match> getAllMatches();

    Match getMatchById(ObjectId id);

    List<Match> getMatchByDate(Date date);

    List<Match> getMatchByTeam(ObjectId teamId);

    void playMatch(ObjectId team1Id, ObjectId team2Id);
}
