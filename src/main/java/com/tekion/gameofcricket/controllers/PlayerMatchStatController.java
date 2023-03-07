package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.helper.ByNameRequestBody;
import com.tekion.gameofcricket.models.PlayerMatchStat;
import com.tekion.gameofcricket.services.PlayerMatchStatService;
import com.tekion.gameofcricket.services.PlayerService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    private List<PlayerMatchStat> getAllPerformancesOfPlayer(@PathVariable String playerId) {
        LOGGER.info("GET call received : http://localhost:3004/stats/" + playerId);
        return playerMatchStatService.getAllPerformancesOfPlayer(new ObjectId(playerId));
    }

    @GetMapping("/byName")
    private List<PlayerMatchStat> getAllPerformancesOfPlayer(@RequestBody ByNameRequestBody requestBody) {
        LOGGER.info("GET call received : http://localhost:3004/stats/byName for \"" + requestBody.getName() + '\"');
        ObjectId playerId = playerService.getPlayerByName(requestBody.getName()).getId();
        return playerMatchStatService.getAllPerformancesOfPlayer(playerId);
    }

    @GetMapping("/{playerId}/{matchId}")
    private PlayerMatchStat getPlayerStatByMatch(@PathVariable String playerId, @PathVariable String matchId) {
        LOGGER.info("GET call received : http://localhost:3004/stats/" + playerId + '/' + matchId);
        return playerMatchStatService.getPlayerPerformanceByMatch(new ObjectId(playerId), new ObjectId(matchId));
    }

    @GetMapping("/byName/{matchId}")
    private PlayerMatchStat getPlayerStatByMatch(@RequestBody ByNameRequestBody requestBody, @PathVariable String matchId) {
        LOGGER.info("GET call received : http://localhost:3004/stats/byName/" + matchId + " for \"" + requestBody.getName() + '\"');
        ObjectId playerId = playerService.getPlayerByName(requestBody.getName()).getId();
        return playerMatchStatService.getPlayerPerformanceByMatch(playerId, new ObjectId(matchId));
    }
}
