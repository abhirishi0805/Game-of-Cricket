package Views;

import Models.Match;

public class MatchView {
    private static MatchView instance;

    private MatchView() { }

    public static MatchView getInstance() {
        if(instance == null) {
            instance = new MatchView();
        }
        return instance;
    }

    public void showMatchSummary(Match match) {
        if(match.getFirstInnings().getRunsScored() > match.getSecondInnings().getRunsScored()) {
            System.out.println("Hurray, " + match.getTeamA().getTeamName() + " wins !");
        }
        else if(match.getFirstInnings().getRunsScored() < match.getSecondInnings().getRunsScored()) {
            System.out.println("Hurray, " + match.getTeamB().getTeamName() + " wins !");
        }
        else {
            System.out.println("The match as ended a tie !");
        }

        System.out.println("\033[0;1m" + "\nMatch Summary :");
        System.out.println(match.getTeamA().getTeamName() + " - " + match.getFirstInnings().getRunsScored() + "/" + match.getFirstInnings().getWicketsLost());
        System.out.println(match.getTeamB().getTeamName() + " - " + match.getSecondInnings().getRunsScored() + "/" + match.getSecondInnings().getWicketsLost());

        // TODO - player level summary
    }
}
