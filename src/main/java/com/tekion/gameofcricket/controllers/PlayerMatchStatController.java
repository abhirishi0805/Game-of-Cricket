package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.PlayerMatchStat;
import com.tekion.gameofcricket.services.PlayerMatchStatService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/performances")
public class PlayerMatchStatController {

    @Autowired
    private PlayerMatchStatService playerMatchStatService;

    @GetMapping("/{playerId}")
    private List<PlayerMatchStat> getAllPerformancesOfPlayer(@PathVariable String playerId) {
        return playerMatchStatService.getAllPerformancesOfPlayer(new ObjectId(playerId));
    }

    @GetMapping("/{playerId}/{matchId}")
    private PlayerMatchStat getPlayerStatByMatch(@PathVariable String playerId, @PathVariable String matchId) {
        return playerMatchStatService.getPlayerPerformanceByMatch(new ObjectId(playerId), new ObjectId(matchId));
    }
}
