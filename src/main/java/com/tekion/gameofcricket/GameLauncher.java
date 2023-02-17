package com.tekion.gameofcricket;

import com.tekion.gameofcricket.controllers.MainController;

public class GameLauncher {

    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.run();
    }
}
