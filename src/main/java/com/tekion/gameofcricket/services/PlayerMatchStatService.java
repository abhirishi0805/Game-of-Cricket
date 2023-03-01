package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.PlayerMatchStat;
import org.bson.types.ObjectId;

import java.util.List;

public interface PlayerMatchStatService {

    void updateBattingFigure(PlayerMatchStat playerMatchStat, int outcome);

    void updateBowlingFigure(PlayerMatchStat playerMatchStat, int outcome);

    void addPlayerMatchStat(PlayerMatchStat playerMatchStat);

    List<PlayerMatchStat> getAllPerformancesOfPlayer(ObjectId playerId);

    PlayerMatchStat getPlayerPerformanceByMatch(ObjectId playerId, ObjectId matchId);
}
