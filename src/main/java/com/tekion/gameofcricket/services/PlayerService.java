package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.PlayerMatchStat;
import org.bson.types.ObjectId;

import java.util.List;

public interface PlayerService {

    void addPlayer(Player player);

    List<Player> getAllPlayers();

    Player getPlayerById(ObjectId id);

    void updatePlayerDataPostMatch(ObjectId id, PlayerMatchStat playerMatchStat);
}
