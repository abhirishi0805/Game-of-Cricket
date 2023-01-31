package Views;

import Models.Player;

public class PlayerView {
    private static PlayerView instance;

    private PlayerView() { }

    public static PlayerView getInstance() {
        if(instance == null) {
            instance = new PlayerView();
        }
        return instance;
    }

    public void printBestPerformers(Player bestBatsman, int runsScored, Player bestBowler, int wicketsTaken) {
        System.out.println("\nBest Performers : ");
        System.out.print("\033[0;1m" + "\tBest Batsman - ");
        System.out.println(bestBatsman.getPlayerName() + " (" + runsScored + " runs)");
        System.out.print("\033[0;1m" + "\tBest Bowler - ");
        System.out.println(bestBowler.getPlayerName() + " (" + wicketsTaken + " wickets)");
    }
}
