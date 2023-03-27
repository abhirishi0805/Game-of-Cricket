package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.services.PlayerService;
import com.tekion.gameofcricket.responsebody.GenericResponseDto;
import com.tekion.gameofcricket.services.ResponseMappingService;
import com.tekion.gameofcricket.utility.enums.ResponseStatus;
import com.tekion.gameofcricket.utility.InputVerifier;
import com.tekion.gameofcricket.requestbody.PlayerRequestDto;
import com.tekion.gameofcricket.responsebody.PlayerResponseDto;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;
    @Autowired
    private ResponseMappingService responseMappingService;

    @GetMapping()
    public ResponseEntity<List<PlayerResponseDto>> getAllPlayers() {
        LOGGER.info("GET call received : http://localhost:3004/players");
        List<Player> result = playerService.getAllPlayers();
        return ResponseEntity.ok(
                result.stream().map(responseMappingService::mapPlayer).collect(Collectors.toUnmodifiableList()));
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResponseDto> getPlayerById(@PathVariable String playerId) {
        LOGGER.info("GET call received : http://localhost:3004/players/" + playerId);
        InputVerifier.validatePlayerId(playerId);
        Player result = playerService.getPlayerById(new ObjectId(playerId));
        return ResponseEntity.ok(responseMappingService.mapPlayer(result));
    }

    @GetMapping("/player-name")
    public ResponseEntity<PlayerResponseDto> getPlayerByName(@RequestBody PlayerRequestDto requestBody) {
        LOGGER.info(
                "GET call received : http://localhost:3004/players/byName for \"" + requestBody.getPlayerName() + '\"');
        InputVerifier.validatePlayerRequestBody(requestBody);
        Player result = playerService.getPlayerByName(requestBody.getPlayerName());
        return ResponseEntity.ok(responseMappingService.mapPlayer(result));
    }

    @PostMapping()
    public ResponseEntity<GenericResponseDto> addPlayer(@RequestBody PlayerRequestDto requestBody) {
        LOGGER.info("POST call received : http://localhost:3004/players");
        InputVerifier.validatePlayerRequestBody(requestBody);
        playerService.addPlayer(requestBody.getPlayerName());
        return ResponseEntity.ok(new GenericResponseDto(ResponseStatus.SUCCESS, "Player successfully created"));
    }
}
