package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.POST, value = "/players")
    public void addPlayer(@RequestBody Player player) {
        playerService.addPlayer(player);
    }

    @RequestMapping("/players")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @RequestMapping("/players/{id}")
    public Player getPlayerById(@PathVariable String id) {
        return playerService.getPlayerById(id);
    }
}
