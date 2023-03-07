package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.helper.ByNameRequestBody;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.services.PlayerService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;

    @PostMapping()
    public void addPlayer(@RequestBody Player player) {
        LOGGER.info("POST call received : http://localhost:3004/players");
        playerService.addPlayer(player);
    }

    @GetMapping()
    public List<Player> getAllPlayers() {
        LOGGER.info("GET call received : http://localhost:3004/players");
        return playerService.getAllPlayers();
    }

    @GetMapping("/{playerId}")
    public Player getPlayerById(@PathVariable String playerId) {
        LOGGER.info("GET call received : http://localhost:3004/players/" + playerId);
        return playerService.getPlayerById(new ObjectId(playerId));
    }

    @GetMapping("/byName")
    public Player getPlayerByName(@RequestBody ByNameRequestBody requestBody) {
        LOGGER.info("GET call received : http://localhost:3004/players/byName for \"" + requestBody.getName() + '\"');
        return playerService.getPlayerByName(requestBody.getName());
    }
}
