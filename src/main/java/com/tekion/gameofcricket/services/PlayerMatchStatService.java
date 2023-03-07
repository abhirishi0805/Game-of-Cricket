package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.PlayerMatchStat;
import org.bson.types.ObjectId;

import java.util.List;

public interface PlayerMatchStatService {

    List<PlayerMatchStat> getAllStatsOfPlayer(ObjectId playerId);

    List<PlayerMatchStat> getAllStatsOfMatch(ObjectId matchId);

    PlayerMatchStat getPlayerStatByMatch(ObjectId playerId, ObjectId matchId);

    void addPlayerMatchStat(PlayerMatchStat playerMatchStat);

    void updateBattingFigure(PlayerMatchStat playerMatchStat, int outcome);

    void updateBowlingFigure(PlayerMatchStat playerMatchStat, int outcome);
}
