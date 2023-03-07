package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.helper.NameRequestBody;
import com.tekion.gameofcricket.models.PlayerMatchStat;
import com.tekion.gameofcricket.services.PlayerMatchStatService;
import com.tekion.gameofcricket.services.PlayerService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class PlayerMatchStatController {

    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerMatchStatController.class);
    @Autowired
    private PlayerMatchStatService playerMatchStatService;
    @Autowired
    @Lazy
    private PlayerService playerService;

    @GetMapping("/{playerId}")
    private ResponseEntity<List<PlayerMatchStat>> getAllStatsOfPlayer(@PathVariable String playerId) {
        LOGGER.info("GET call received : http://localhost:3004/stats/" + playerId);
        return ResponseEntity.ok(playerMatchStatService.getAllStatsOfPlayer(new ObjectId(playerId)));
    }

    @GetMapping("/byName")
    private ResponseEntity<List<PlayerMatchStat>> getAllStatsOfPlayer(@RequestBody NameRequestBody requestBody) {
        LOGGER.info("GET call received : http://localhost:3004/stats/byName for \"" + requestBody.getName() + '\"');
        ObjectId playerId = playerService.getPlayerByName(requestBody.getName()).getId();
        return ResponseEntity.ok(playerMatchStatService.getAllStatsOfPlayer(playerId));
    }

    @GetMapping("/match/{matchId}")
    private ResponseEntity<List<PlayerMatchStat>> getAllStatsOfMatch(@PathVariable String matchId) {
        LOGGER.info("GET call received : http://localhost:3004/stats/match/" + matchId);
        return ResponseEntity.ok(playerMatchStatService.getAllStatsOfMatch(new ObjectId(matchId)));
    }

    @GetMapping("/{playerId}/{matchId}")
    private ResponseEntity<PlayerMatchStat> getPlayerStatByMatch(@PathVariable String playerId, @PathVariable String matchId) {
        LOGGER.info("GET call received : http://localhost:3004/stats/" + playerId + '/' + matchId);
        return ResponseEntity.ok(playerMatchStatService.getPlayerStatByMatch(new ObjectId(playerId), new ObjectId(matchId)));
    }

    @GetMapping("/byName/{matchId}")
    private ResponseEntity<PlayerMatchStat> getPlayerStatByMatch(@RequestBody NameRequestBody requestBody,
                                                 @PathVariable String matchId) {
        LOGGER.info("GET call received : http://localhost:3004/stats/byName/" + matchId + " for \"" + requestBody.getName() + '\"');
        ObjectId playerId = playerService.getPlayerByName(requestBody.getName()).getId();
        return ResponseEntity.ok(playerMatchStatService.getPlayerStatByMatch(playerId, new ObjectId(matchId)));
    }
}
