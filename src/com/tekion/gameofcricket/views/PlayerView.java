package com.tekion.gameofcricket.views;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.others.Utility;

public class PlayerView {

    private static PlayerView playerView;

    private PlayerView() {
    }

    public static PlayerView getInstance() {
        if (playerView == null) {
            playerView = new PlayerView();
        }
        return playerView;
    }

    public void printBestPerformers(Player bestBatsman, int runsScored, Player bestBowler, int wicketsTaken) {
        String messageToDisplay = "\nBest Performers : \n" + "\tBest Batsman - " + bestBatsman.getPlayerName() + " (" +
                                  runsScored + " runs)\n" + "\tBest Bowler - " + bestBowler.getPlayerName() + " (" +
                                  wicketsTaken + " wickets)";
        Utility.printAndNextLine(messageToDisplay);
    }
}
