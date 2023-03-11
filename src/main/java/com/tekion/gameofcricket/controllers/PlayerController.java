package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.services.PlayerService;
import com.tekion.gameofcricket.utility.ApiResponse;
import com.tekion.gameofcricket.utility.ModelToResponseDtoMapper;
import com.tekion.gameofcricket.utility.ResponseStatus;
import com.tekion.gameofcricket.utility.exceptionhandling.InputVerifier;
import com.tekion.gameofcricket.utility.requestbody.PlayerRequestBody;
import com.tekion.gameofcricket.utility.responsebody.PlayerResponseDTO;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the controller to handle player related API requests
 */
@RestController
@RequestMapping("/players")
public final class PlayerController {

    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;
    @Autowired
    @Lazy
    private ApplicationContext applicationContext;

    @GetMapping()
    public ResponseEntity<List<PlayerResponseDTO>> getAllPlayers() {
        LOGGER.info("GET call received : http://localhost:3004/players");
        List<Player> result = playerService.getAllPlayers();
        return ResponseEntity.ok(result.stream().map(ModelToResponseDtoMapper::mapPlayer).collect(Collectors.toList()));
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResponseDTO> getPlayerById(@PathVariable String playerId) {
        LOGGER.info("GET call received : http://localhost:3004/players/" + playerId);
        InputVerifier.validatePlayerId(playerId);
        Player result = playerService.getPlayerById(new ObjectId(playerId));
        return ResponseEntity.ok(ModelToResponseDtoMapper.mapPlayer(result));
    }

    @GetMapping("/byName")
    public ResponseEntity<PlayerResponseDTO> getPlayerByName(@RequestBody PlayerRequestBody requestBody) {
        LOGGER.info(
                "GET call received : http://localhost:3004/players/byName for \"" + requestBody.getPlayerName() + '\"');
        InputVerifier.validatePlayerRequestBody(requestBody);
        Player result = playerService.getPlayerByName(requestBody.getPlayerName());
        return ResponseEntity.ok(ModelToResponseDtoMapper.mapPlayer(result));
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
