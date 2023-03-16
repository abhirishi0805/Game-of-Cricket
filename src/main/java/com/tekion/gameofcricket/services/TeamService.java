package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.requestbody.CreateTeamRequestDto;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * This interface declares methods to perform team related operations
 */
public interface TeamService {

    /**
     * Returns all the teams available in the repository
     *
     * @return list of all the teams
     */
    List<Team> getAllTeams();

    /**
     * Returns a team by its team ID
     *
     * @param id id of the team to be returned
     * @return team with the corresponding id
     * @throws java.util.NoSuchElementException if the given id doesn't match with any of the teams
     */
    Team getTeamById(ObjectId id);

    /**
     * Returns a team by its name
     *
     * @param teamName name of the team to be returned
     * @return team with the corresponding name
     * @throws java.util.NoSuchElementException if the given name doesn't match with any of the teams
     */
    Team getTeamByName(String teamName);

    /**
     * Returns all the players of a particular team
     *
     * @param id id of the team whose players to be returned
     * @return list of players of that particular team
     */
    List<Player> getTeamPlayers(ObjectId id);

    /**
     * Creates and stores a new team in the repository
     *
     * @param requestBody CreateTeamRequestDto instance containing all required details for the new team
     */
    void addTeam(CreateTeamRequestDto requestBody);

    /**
     * Updates an already exiting team
     *
     * @param team updated team instance
     */
    void updateTeam(Team team);
}
