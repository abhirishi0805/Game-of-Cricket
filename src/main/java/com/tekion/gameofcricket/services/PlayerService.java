package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Player;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * This interface declares methods to perform all player related operations
 */
public interface PlayerService {

    /**
     * Returns all the players available in the repository
     *
     * @return list of all the players
     */
    List<Player> getAllPlayers();

    /**
     * Returns a player by its player ID
     *
     * @param id id of the player to be returned
     * @return player with the corresponding id
     * @throws java.util.NoSuchElementException if the given id doesn't match with any of the players
     */
    Player getPlayerById(ObjectId id);

    /**
     * Returns a player by its name
     *
     * @param playerName name of the player to be returned
     * @return player with the corresponding name
     * @throws java.util.NoSuchElementException if the given name doesn't match with any of the players
     */
    Player getPlayerByName(String playerName);

    /**
     * Creates and stores a new player in the repository
     *
     * @param playerName name of the player to be stored
     */
    void addPlayer(String playerName);

    /**
     * Updates an already exiting player
     *
     * @param player updated player instance
     */
    void updatePlayer(Player player);
}
