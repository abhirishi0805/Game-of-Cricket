package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.PlayerMatchStat;
import com.tekion.gameofcricket.repositories.PlayerRepository;
import com.tekion.gameofcricket.utility.LogUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void addPlayer(Player player) {
        playerRepository.save(player);
        LogUtils.logInfo("New player created : " + player);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayerById(ObjectId id) {
        return playerRepository.findById(id).orElse(null);
    }

    @Override
    public void updatePlayerDataPostMatch(ObjectId id, PlayerMatchStat playerMatchStat) {
        Player player = getPlayerById(id);
        player.setGamesPlayed(player.getGamesPlayed() + 1);
        player.setTotalRunsScored(player.getTotalRunsScored() + playerMatchStat.getRunsScored());
        player.setTotalWicketsTaken(player.getTotalWicketsTaken() + playerMatchStat.getWicketsTaken());
        playerRepository.save(player);
    }
}
