package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.repositories.PlayerRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This is a concrete implementation for the PlayerService interface
 */
@Service
public final class PlayerServiceImpl implements PlayerService {

    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerServiceImpl.class);
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    @Lazy
    private ApplicationContext applicationContext;

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayerById(ObjectId id) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isEmpty()) {
            throw new NoSuchElementException("No player available with id = " + id);
        }
        return player.get();
    }

    @Override
    public Player getPlayerByName(String playerName) {
        Player player = playerRepository.findPlayerByPlayerNameEqualsIgnoreCase(playerName);
        if (player == null) {
            throw new NoSuchElementException("No player available with name = " + playerName);
        }
        return player;
    }

    @Override
    public void addPlayer(String playerName) {
        Player player = new Player(playerName);
        playerRepository.save(player);
        LOGGER.info("New player created : " + player);
    }

    @Override
    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }
}
