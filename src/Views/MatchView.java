package Views;

import Models.Match;
import Others.Utility;

public class MatchView {
    private static MatchView matchViewInstance;

    private MatchView() { }

    public static MatchView getInstance() {
        if (matchViewInstance == null) {
            matchViewInstance = new MatchView();
        }
        return matchViewInstance;
    }

    public void showMatchSummary(Match match) {
        String messageToDisplay;
        if (match.getFirstInnings().getRunsScored() > match.getSecondInnings().getRunsScored()) {
            messageToDisplay = "Hurray, " + match.getTeamA().getTeamName() + " wins !";
        } else if (match.getFirstInnings().getRunsScored() < match.getSecondInnings().getRunsScored()) {
            messageToDisplay = "Hurray, " + match.getTeamB().getTeamName() + " wins !";
        } else {
            messageToDisplay = "The match as ended a tie !";
        }

        messageToDisplay += "\n\nMatch Summary :\n" +
                match.getTeamA().getTeamName() + " - " + match.getFirstInnings().getRunsScored() + "/" + match.getFirstInnings().getWicketsLost() + "\n" +
                match.getTeamB().getTeamName() + " - " + match.getSecondInnings().getRunsScored() + "/" + match.getSecondInnings().getWicketsLost();

        Utility.printAndNextLine(messageToDisplay);

        // TODO - player level summary
    }
}
