package Others;

import Models.Player;
import Models.Team;
import Services.MatchService;
import Services.PlayerService;
import Services.TeamService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        System.out.println("\033[0;1m" + "\nMain Menu -");
        System.out.println("\t1 --> Show points table\n" +
                "\t2 --> Show best individual performers\n" +
                "\t3 --> Compete between two teams\n" +
                "\t4 --> Quit game");
        System.out.print("\nEnter the option number to proceed : ");

        String optionNumberSelected = "0";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            optionNumberSelected = bufferedReader.readLine().trim();
        }
        catch (IOException e) { System.out.println("IOException: " + e); }

        // TODO - check for invalid input

        switch (optionNumberSelected) {
            case "1" -> showPointsTable();
            case "2" -> showBestPerformers();
            case "3" -> playMatch();
            case "4" -> showExitMessage();
        }
    }

    private void showPointsTable() {
        ArrayList<Team> teamsList = repository.getTeamsList();
        teamService.showPointsTable(teamsList);
        showActionCompletedDialog();
    }

    private void showBestPerformers() {
        ArrayList<Player> playersList = repository.getPlayersList();
        playerService.showBestPerformers(playersList);
        showActionCompletedDialog();
    }

    private void playMatch() {
        ArrayList<Team> teamsList = repository.getTeamsList();
        matchService.manageMatch(teamsList);
        showActionCompletedDialog();
    }

    private void showActionCompletedDialog() {
        System.out.print("\nEnter 1 to go to Main Menu or anything else to exit : ");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            if(bufferedReader.readLine().trim().equals("1")) {
                loadMainMenu();
            }
            else { showExitMessage(); }
        }
        catch (IOException e) { System.out.println("IOException: " + e); }
    }

    private void showStartMessage() {
        System.out.println("\033[0;1m" + "\nWelcome to the Cricket Game");
    }

    private void showExitMessage() {
        System.out.println("\033[0;1m" + "\nThe game has ended !");
    }
}
