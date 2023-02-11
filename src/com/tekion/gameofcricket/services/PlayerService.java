package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.Repository;
import com.tekion.gameofcricket.others.Utility;
import com.tekion.gameofcricket.views.PlayerView;

import java.util.List;

public class PlayerService {

    private static PlayerService playerService;
    private List<Player> playersList;

    private PlayerService() {
    }

    public static PlayerService getInstance() {
        if (playerService == null) {
            playerService = new PlayerService();
        }
        return playerService;
    }

    public void showBestPerformers() {
        playersList = Repository.getInstance().getPlayersList();

        if (playersList.size() == 0) {
            Utility.printAndNextLine("No player data to show !");
            return;
        }

        // highest run scorer --> best batsman
        // highest wicket taker --> best bowler

        // TODO - edge case for multiple best performers

        Player bestBatsman = playersList.get(0);
        int maximumRunsScored = getTotalRunsScored(bestBatsman);

        Player bestBowler = playersList.get(0);
        int maximumWicketsTaken = getTotalWicketsTaken(bestBowler);

        for (Player player : playersList) {
            int runsScored = getTotalRunsScored(player), wicketsTaken = getTotalWicketsTaken(player);
            if (runsScored > maximumRunsScored) {
                bestBatsman = player;
                maximumRunsScored = runsScored;
            }
            if (wicketsTaken > maximumWicketsTaken) {
                bestBowler = player;
                maximumWicketsTaken = wicketsTaken;
            }
        }

        PlayerView.getInstance().printBestPerformers(bestBatsman, maximumRunsScored, bestBowler, maximumWicketsTaken);
    }

    private int getTotalRunsScored(Player player) {
        int totalRunsScored = 0;
        List<Integer> runsScoredByMatch = player.getRunsScoredByMatch();

        for (int runsScoredThisMatch : runsScoredByMatch) {
            totalRunsScored += runsScoredThisMatch;
        }
        return totalRunsScored;
    }

    private int getTotalWicketsTaken(Player player) {
        int totalWicketsTaken = 0;
        List<Integer> wicketsTakenByMatch = player.getWicketsTakenByMatch();

        for (int wicketsTakenThisMatch : wicketsTakenByMatch) {
            totalWicketsTaken += wicketsTakenThisMatch;
        }
        return totalWicketsTaken;
    }

    public void addBattingFigure(int playerId, int runsScored) {
        Repository.getInstance().getPlayerById(playerId).getRunsScoredByMatch().add(runsScored);
    }

    public void addBowlingFigure(int playerId, int wicketsTaken) {
        Repository.getInstance().getPlayerById(playerId).getWicketsTakenByMatch().add(wicketsTaken);
    }
}
