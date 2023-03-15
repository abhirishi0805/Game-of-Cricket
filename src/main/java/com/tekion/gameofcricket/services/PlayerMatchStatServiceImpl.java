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
public final class PlayerMatchStatServiceImpl implements PlayerMatchStatService {

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

    @Override
    public void updateBattingFigure(Stat stat, int outcome) {
        stat.setBallsFaced(stat.getBallsFaced() + 1);
        if (outcome != 7) {
            stat.setRunsScored(stat.getRunsScored() + outcome);
        }
        if (outcome == 6) {
            stat.setSixesHit(stat.getSixesHit() + 1);
        }
        if (outcome == 4) {
            stat.setFoursHit(stat.getFoursHit() + 1);
        }
    }

    @Override
    public void updateBowlingFigure(Stat stat, int outcome) {
        stat.setBallsThrown(stat.getBallsThrown() + 1);
        if (outcome == 7) {
            stat.setWicketsTaken(stat.getWicketsTaken() + 1);
        } else {
            stat.setRunsConceded(stat.getRunsConceded() + 1);
        }
    }
}
