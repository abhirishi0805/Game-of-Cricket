package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.services.TeamService;

class TeamController {

    public void showPointsTable() {
        new TeamService().showPointsTable();
    }
}