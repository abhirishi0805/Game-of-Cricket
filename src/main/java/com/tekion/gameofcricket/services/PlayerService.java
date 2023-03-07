package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Player;
import org.bson.types.ObjectId;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    Player getPlayerById(ObjectId id);

    Player getPlayerByName(String playerName);

    void addPlayer(String playerName);

    void updatePlayer(Player player);
}
