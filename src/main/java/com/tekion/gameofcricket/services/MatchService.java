package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Match;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * This interface declares methods to perform all match related operations (except starting a new match)
 */
public interface MatchService {

    /**
     * Returns all the matches available in the repository
     *
     * @return list of all the matches
     */
    List<Match> getAllMatches();

    /**
     * Returns a match by its match ID
     *
     * @param id id of the match to be returned
     * @return match with the corresponding id
     * @throws java.util.NoSuchElementException if the given id doesn't match with any of the matches
     */
    Match getMatchById(ObjectId id);

    /**
     * Returns all matches played by a particular team
     *
     * @param teamId id of the team whose matches to be returned
     * @return list of all matches of the given team
     * @throws java.util.NoSuchElementException if the given teamId doesn't match with any of the teams
     */
    List<Match> getMatchByTeam(ObjectId teamId);

    /**
     * Returns all matches played on a particular date
     *
     * @param date date for which matches to be returned; in format DD-MM-YYYY
     * @return list of all matches played on the given date
     */
    List<Match> getMatchByDate(String date);

    /**
     * Stores a new match in the repository
     *
     * @param match match that needs to be stored
     */
    void addMatch(Match match);
}