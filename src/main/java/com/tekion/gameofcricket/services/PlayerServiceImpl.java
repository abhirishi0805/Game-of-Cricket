package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.PlayerMatchStat;
import com.tekion.gameofcricket.repositories.PlayerRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void addPlayer(Player player) {
        playerRepository.save(player);
        LOGGER.info("New player created : " + player);
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

    @Override
    public Player getPlayerByName(String playerName) {
        return playerRepository.findPlayerByPlayerName(playerName);
    }
}
