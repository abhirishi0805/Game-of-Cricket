package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.PlayerMatchStat;
import com.tekion.gameofcricket.services.PlayerMatchStatService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performances")
public class PlayerMatchStatController {

    @Lazy
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerMatchStatController.class);

    @Autowired
    private PlayerMatchStatService playerMatchStatService;

    @GetMapping("/{playerId}")
    private List<PlayerMatchStat> getAllPerformancesOfPlayer(@PathVariable String playerId) {
        LOGGER.info("GET call received : http://localhost:3004/performances/" + playerId);
        return playerMatchStatService.getAllPerformancesOfPlayer(new ObjectId(playerId));
    }

    @GetMapping("/{playerId}/{matchId}")
    private PlayerMatchStat getPlayerStatByMatch(@PathVariable String playerId, @PathVariable String matchId) {
        LOGGER.info("GET call received : http://localhost:3004/performances/" + playerId + '/' + matchId);
        return playerMatchStatService.getPlayerPerformanceByMatch(new ObjectId(playerId), new ObjectId(matchId));
    }
}
