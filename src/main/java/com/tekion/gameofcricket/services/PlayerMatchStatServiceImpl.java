package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.PlayerMatchStat;
import com.tekion.gameofcricket.repositories.PlayerMatchStatRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerMatchStatServiceImpl implements PlayerMatchStatService {

    @Autowired
    private PlayerMatchStatRepository playerMatchStatRepository;

    @Override
    public void updateBattingFigure(PlayerMatchStat playerMatchStat, int outcome) {
        playerMatchStat.setBallsFaced(playerMatchStat.getBallsFaced() + 1);
        if (outcome != 7) {
            playerMatchStat.setRunsScored(playerMatchStat.getRunsScored() + outcome);
        }
        if (outcome == 6) {
            playerMatchStat.setSixesHit(playerMatchStat.getSixesHit() + 1);
        }
        if (outcome == 4) {
            playerMatchStat.setFoursHit(playerMatchStat.getFoursHit() + 1);
        }
    }

    @Override
    public void updateBowlingFigure(PlayerMatchStat playerMatchStat, int outcome) {
        playerMatchStat.setBallsThrown(playerMatchStat.getBallsThrown() + 1);
        if (outcome == 7) {
            playerMatchStat.setWicketsTaken(playerMatchStat.getWicketsTaken() + 1);
        } else {
            playerMatchStat.setRunsConceded(playerMatchStat.getRunsConceded() + 1);
        }
    }

    @Override
    public void addPlayerMatchStat(PlayerMatchStat playerMatchStat) {
        playerMatchStatRepository.save(playerMatchStat);
    }

    @Override
    public List<PlayerMatchStat> getAllPerformancesOfPlayer(ObjectId playerId) {
        return playerMatchStatRepository.findPlayerMatchStatsByPlayerId(playerId);
    }

    @Override
    public PlayerMatchStat getPlayerPerformanceByMatch(ObjectId playerId, ObjectId matchId) {
        return playerMatchStatRepository.findPlayerMatchStatByPlayerIdAndMatchId(playerId, matchId);
    }
}
