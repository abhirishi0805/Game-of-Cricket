package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Stat;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * This interface declares methods to perform all stats related operations
 */
public interface StatService {

    /**
     * Returns all stats for any particular player
     *
     * @param playerId id of the player whose stats need to be returned
     * @return list of stats of the specified player
     */
    List<Stat> getAllStatsOfPlayer(ObjectId playerId);

    /**
     * Returns all stats for a particular match
     *
     * @param matchId id of the match whose stats need to be returned
     * @return list of stats of the specified match
     */
    List<Stat> getAllStatsOfMatch(ObjectId matchId);

    /**
     * Returns stat of a player in a particular match
     *
     * @param playerId id of the player whose stat needs to be returned
     * @param matchId  id of the match whose stat needs to be returned
     * @return stat of the specified player in the specified match
     */
    Stat getPlayerStatByMatch(ObjectId playerId, ObjectId matchId);

    /**
     * Stores a new stat in the repository
     *
     * @param stat stat that needs to be stored
     */
    void addPlayerMatchStat(Stat stat);
}
