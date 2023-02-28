package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.services.PlayerMatchStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerMatchStatController {
    @Autowired
    private PlayerMatchStatService playerMatchStatService;

}
