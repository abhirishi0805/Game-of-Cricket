package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Stat;
import com.tekion.gameofcricket.repositories.StatRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is a concrete implementation for the PlayerMatchStatService interface
 */
@Service
public final class StatServiceImpl implements StatService {

    @Autowired
    private StatRepository statRepository;

    @Override
    public List<Stat> getAllStatsOfPlayer(ObjectId playerId) {
        return statRepository.findPlayerMatchStatsByPlayerId(playerId);
    }

    @Override
    public List<Stat> getAllStatsOfMatch(ObjectId matchId) {
        return statRepository.findPlayerMatchStatsByMatchId(matchId);
    }

    @Override
    public Stat getPlayerStatByMatch(ObjectId playerId, ObjectId matchId) {
        return statRepository.findPlayerMatchStatByPlayerIdAndMatchId(playerId, matchId);
    }

    @Override
    public void addPlayerMatchStat(Stat stat) {
        statRepository.save(stat);
    }
}