package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.services.PlayerService;
import com.tekion.gameofcricket.utility.LogUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/")
    public void addPlayer(@RequestBody Player player) {
        LogUtils.logApiCall(RequestMethod.POST, "/players/");
        playerService.addPlayer(player);
    }

    @GetMapping("/")
    public List<Player> getAllPlayers() {
        LogUtils.logApiCall(RequestMethod.GET, "/players/");
        return playerService.getAllPlayers();
    }

    @GetMapping("/{playerId}")
    public Player getPlayerById(@PathVariable String playerId) {
        LogUtils.logApiCall(RequestMethod.GET, "/players/" + playerId);
        return playerService.getPlayerById(new ObjectId(playerId));
    }
}
