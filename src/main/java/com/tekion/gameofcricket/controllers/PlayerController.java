package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.services.PlayerService;

class PlayerController {

    public void showBestPerformers() {
        new PlayerService().showBestPerformers();
    }
}
