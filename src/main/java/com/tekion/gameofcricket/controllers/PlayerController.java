package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.services.PlayerService;
import com.tekion.gameofcricket.utility.ApiResponse;
import com.tekion.gameofcricket.utility.ResponseStatus;
import com.tekion.gameofcricket.utility.exceptionhandling.InputVerifier;
import com.tekion.gameofcricket.utility.requestbody.PlayerRequestBody;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
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
    @Autowired
    @Lazy
    private ApplicationContext applicationContext;

    @GetMapping()
    public ResponseEntity<List<Player>> getAllPlayers() {
        LOGGER.info("GET call received : http://localhost:3004/players");
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String playerId) {
        LOGGER.info("GET call received : http://localhost:3004/players/" + playerId);
        InputVerifier.validatePlayerId(playerId);
        return ResponseEntity.ok(playerService.getPlayerById(new ObjectId(playerId)));
    }

    @GetMapping("/byName")
    public ResponseEntity<Player> getPlayerByName(@RequestBody PlayerRequestBody requestBody) {
        LOGGER.info(
                "GET call received : http://localhost:3004/players/byName for \"" + requestBody.getPlayerName() + '\"');
        InputVerifier.validatePlayerRequestBody(requestBody);
        return ResponseEntity.ok(playerService.getPlayerByName(requestBody.getPlayerName()));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> addPlayer(@RequestBody PlayerRequestBody requestBody) {
        LOGGER.info("POST call received : http://localhost:3004/players");
        InputVerifier.validatePlayerRequestBody(requestBody);
        playerService.addPlayer(requestBody.getPlayerName());
        ApiResponse response = applicationContext.getBean(ApiResponse.class);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("Player successfully created");
        return ResponseEntity.ok(response);
    }
}
