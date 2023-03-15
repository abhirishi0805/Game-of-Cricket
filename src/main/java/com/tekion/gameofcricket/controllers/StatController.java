package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Stat;
import com.tekion.gameofcricket.responsebody.StatResponseDto;
import com.tekion.gameofcricket.services.PlayerMatchStatService;
import com.tekion.gameofcricket.services.PlayerService;
import com.tekion.gameofcricket.services.ResponseMappingService;
import com.tekion.gameofcricket.utility.InputVerifier;
import com.tekion.gameofcricket.requestbody.PlayerRequestDto;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the controller to handle stats related API requests
 */
@RestController
@RequestMapping("/stats")
public final class StatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatController.class);
    @Autowired
    private PlayerMatchStatService playerMatchStatService;
    @Autowired
    @Lazy
    private PlayerService playerService;
    @Autowired
    private ResponseMappingService responseMappingService;

    @GetMapping("/{playerId}")
    private ResponseEntity<List<StatResponseDto>> getAllStatsOfPlayer(@PathVariable String playerId) {
        LOGGER.info("GET call received : http://localhost:3004/stats/" + playerId);
        InputVerifier.validatePlayerId(playerId);
        List<Stat> result = playerMatchStatService.getAllStatsOfPlayer(new ObjectId(playerId));
        return ResponseEntity.ok(result.stream().map(responseMappingService::mapStat).collect(Collectors.toList()));
    }

    @GetMapping("/player-name")
    private ResponseEntity<List<StatResponseDto>> getAllStatsOfPlayer(@RequestBody PlayerRequestDto requestBody) {
        LOGGER.info(
                "GET call received : http://localhost:3004/stats/byName for \"" + requestBody.getPlayerName() + '\"');
        InputVerifier.validatePlayerRequestBody(requestBody);
        ObjectId playerId = playerService.getPlayerByName(requestBody.getPlayerName()).getId();
        List<Stat> result = playerMatchStatService.getAllStatsOfPlayer(playerId);
        return ResponseEntity.ok(result.stream().map(responseMappingService::mapStat).collect(Collectors.toList()));
    }

    @GetMapping("/match/{matchId}")
    private ResponseEntity<List<StatResponseDto>> getAllStatsOfMatch(@PathVariable String matchId) {
        LOGGER.info("GET call received : http://localhost:3004/stats/match/" + matchId);
        InputVerifier.validateMatchId(matchId);
        List<Stat> result = playerMatchStatService.getAllStatsOfMatch(new ObjectId(matchId));
        return ResponseEntity.ok(result.stream().map(responseMappingService::mapStat).collect(Collectors.toList()));
    }

    @GetMapping("/{playerId}/{matchId}")
    private ResponseEntity<StatResponseDto> getPlayerStatByMatch(@PathVariable String playerId,
                                                                 @PathVariable String matchId) {
        LOGGER.info("GET call received : http://localhost:3004/stats/" + playerId + '/' + matchId);
        InputVerifier.validatePlayerId(playerId);
        InputVerifier.validateMatchId(matchId);
        Stat result = playerMatchStatService.getPlayerStatByMatch(new ObjectId(playerId), new ObjectId(matchId));
        return ResponseEntity.ok(responseMappingService.mapStat(result));
    }

    @GetMapping("/player-name/{matchId}")
    private ResponseEntity<StatResponseDto> getPlayerStatByMatch(@RequestBody PlayerRequestDto requestBody,
                                                                 @PathVariable String matchId) {
        LOGGER.info("GET call received : http://localhost:3004/stats/byName/" + matchId + " for \"" +
                    requestBody.getPlayerName() + '\"');
        InputVerifier.validatePlayerRequestBody(requestBody);
        InputVerifier.validateMatchId(matchId);
        ObjectId playerId = playerService.getPlayerByName(requestBody.getPlayerName()).getId();
        Stat result = playerMatchStatService.getPlayerStatByMatch(playerId, new ObjectId(matchId));
        return ResponseEntity.ok(responseMappingService.mapStat(result));
    }
}
