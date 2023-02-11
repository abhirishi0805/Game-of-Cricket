package com.tekion.gameofcricket;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.others.Constants;
import com.tekion.gameofcricket.others.Utility;
import com.tekion.gameofcricket.services.MatchService;
import com.tekion.gameofcricket.services.PlayerService;
import com.tekion.gameofcricket.services.TeamService;

import java.util.List;

public class Controller {

    private PlayerService playerService;
    private TeamService teamService;
    private MatchService matchService;
    private Repository repository;

    public Controller() {
        playerService = PlayerService.getInstance();
        teamService = TeamService.getInstance();
        matchService = MatchService.getInstance();
        repository = Repository.getInstance();
    }

    public void startGame() {
        showStartMessage();
        loadMainMenu();
    }

    private void loadMainMenu() {
        Utility.printAndNextLine(Constants.MAIN_MENU);

        String optionNumberSelected = Utility.getUserInput("\nEnter the option number to proceed : ");

        // TODO - check for invalid input

        switch (optionNumberSelected) {
            case "1" -> showPointsTable();
            case "2" -> showBestPerformers();
            case "3" -> playMatch();
            case "4" -> showExitMessage();
        }
    }

    private void showPointsTable() {
        List<Team> teamsList = repository.getTeamsList();
        teamService.showPointsTable(teamsList);
        showActionCompletedDialog();
    }

    private void showBestPerformers() {
        List<Player> playersList = repository.getPlayersList();
        playerService.showBestPerformers(playersList);
        showActionCompletedDialog();
    }

    private void playMatch() {
        List<Team> teamsList = repository.getTeamsList();
        matchService.organizeMatch(teamsList);
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
