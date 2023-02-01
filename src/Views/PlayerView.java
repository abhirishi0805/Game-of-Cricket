package Views;

import Models.Player;
import Others.Utility;

public class PlayerView {
    private static PlayerView playerViewInstance;

    private PlayerView() { }

    public static PlayerView getInstance() {
        if (playerViewInstance == null) {
            playerViewInstance = new PlayerView();
        }
        return playerViewInstance;
    }

    public void printBestPerformers(Player bestBatsman, int runsScored, Player bestBowler, int wicketsTaken) {
        String messageToDisplay = "\nBest Performers : \n" +
                "\tBest Batsman - " + bestBatsman.getPlayerName() + " (" + runsScored + " runs)\n" +
                "\tBest Bowler - " + bestBowler.getPlayerName() + " (" + wicketsTaken + " wickets)";
        Utility.printAndNextLine(messageToDisplay);
    }
}
