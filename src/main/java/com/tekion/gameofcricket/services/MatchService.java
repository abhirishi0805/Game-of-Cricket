package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Match;

import java.util.List;

public interface MatchService {

    List<Match> getAllMatches();

    Match getMatchById(String id);

    List<Match> getMatchByDate(String date);

    List<Match> getMatchByTeam(String teamId);
}
