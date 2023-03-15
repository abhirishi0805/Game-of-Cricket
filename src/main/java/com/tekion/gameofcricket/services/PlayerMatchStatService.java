package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Stat;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * This interface declares methods to perform stats related operations
 */
public interface PlayerMatchStatService {

    List<Stat> getAllStatsOfPlayer(ObjectId playerId);

    List<Stat> getAllStatsOfMatch(ObjectId matchId);

    Stat getPlayerStatByMatch(ObjectId playerId, ObjectId matchId);

    void addPlayerMatchStat(Stat stat);

    void updateBattingFigure(Stat stat, int outcome);

    void updateBowlingFigure(Stat stat, int outcome);
}
