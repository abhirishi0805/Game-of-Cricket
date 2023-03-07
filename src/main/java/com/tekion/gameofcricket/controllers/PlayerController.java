package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.helper.NameRequestBody;
import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.services.PlayerService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;

    @GetMapping()
    public ResponseEntity<List<Player>> getAllPlayers() {
        LOGGER.info("GET call received : http://localhost:3004/players");
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String playerId) {
        LOGGER.info("GET call received : http://localhost:3004/players/" + playerId);
        return ResponseEntity.ok(playerService.getPlayerById(new ObjectId(playerId)));
    }

    @GetMapping("/byName")
    public ResponseEntity<Player> getPlayerByName(@RequestBody NameRequestBody requestBody) {
        LOGGER.info("GET call received : http://localhost:3004/players/byName for \"" + requestBody.getName() + '\"');
        return ResponseEntity.ok(playerService.getPlayerByName(requestBody.getName()));
    }

    @PostMapping()
    public ResponseEntity<Void> addPlayer(@RequestBody NameRequestBody requestBody) {
        LOGGER.info("POST call received : http://localhost:3004/players");
        playerService.addPlayer(requestBody.getName());
        return ResponseEntity.created(null).build();
    }
}
