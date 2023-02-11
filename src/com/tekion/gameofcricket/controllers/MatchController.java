package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.services.MatchService;

public class MatchController {

    public void playMatch() {
        MatchService.getInstance().organizeMatch();
    }
}
