package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Player;
import org.bson.types.ObjectId;

import java.util.List;

public interface PlayerService {

    void addPlayer(Player player);

    List<Player> getAllPlayers();

    Player getPlayerById(String id);
}
