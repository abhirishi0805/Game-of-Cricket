package com.tekion.gameofcricket.controllers;

import com.tekion.gameofcricket.others.Constants;
import com.tekion.gameofcricket.others.Utility;

public class MainController {

    public void run() {
        showStartMessage();
        loadMainMenu();
    }

    private void loadMainMenu() {
        Utility.printAndNextLine(Constants.MAIN_MENU);

        String optionNumberSelected = Utility.getUserInput("\nEnter the option number to proceed : ");

        // TODO - check for invalid input

        switch (optionNumberSelected) {
            case "1" : showPointsTable();   break;
            case "2" : showBestPerformers();    break;
            case "3" : playMatch();    break;
            case "4" : showExitMessage();
        }
    }

    private void showPointsTable() {
        new TeamController().showPointsTable();
        showActionCompletedDialog();
    }

    private void showBestPerformers() {
        new PlayerController().showBestPerformers();
        showActionCompletedDialog();
    }

    private void playMatch() {
        new MatchController().playMatch();
        showActionCompletedDialog();
    }

    private void showActionCompletedDialog() {
        if (Utility.getUserInput("\nEnter 1 to go to Main Menu or anything else to exit : ").equals("1")) {
            loadMainMenu();
        } else {
            showExitMessage();
        }
    }

    private void showStartMessage() {
        Utility.printAndNextLine(Constants.START_MESSAGE);
    }

    private void showExitMessage() {
        Utility.printAndNextLine(Constants.EXIT_MESSAGE);
    }
}
