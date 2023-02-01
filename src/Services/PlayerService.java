package Services;

import Models.Player;
import Others.Repository;
import Others.Utility;
import Views.PlayerView;

import java.util.ArrayList;

public class PlayerService {
    private static PlayerService playerServiceInstance;

    private PlayerService() { }

    public static PlayerService getInstance() {
        if (playerServiceInstance == null) {
            playerServiceInstance = new PlayerService();
        }
        return playerServiceInstance;
    }

    public void showBestPerformers(ArrayList<Player> playersList) {
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
        ArrayList<Integer> runsScoredByMatch = player.getRunsScoredByMatch();

        for (int runsScoredThisMatch : runsScoredByMatch) {
            totalRunsScored += runsScoredThisMatch;
        }
        return totalRunsScored;
    }

    private int getTotalWicketsTaken(Player player) {
        int totalWicketsTaken = 0;
        ArrayList<Integer> wicketsTakenByMatch = player.getWicketsTakenByMatch();

        for (int wicketsTakenThisMatch : wicketsTakenByMatch) {
            totalWicketsTaken += wicketsTakenThisMatch;
        }
        return totalWicketsTaken;
    }

    public void addBattingFigure(int playerID, int runsScored) {
        Repository.getInstance().getPlayerByID(playerID).getRunsScoredByMatch().add(runsScored);
    }

    public void addBowlingFigure(int playerID, int wicketsTaken) {
        Repository.getInstance().getPlayerByID(playerID).getWicketsTakenByMatch().add(wicketsTaken);
    }
}
