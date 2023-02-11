package com.tekion.gameofcricket.views;

import com.tekion.gameofcricket.models.Match;
import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.Repository;
import com.tekion.gameofcricket.others.Utility;

import java.util.HashMap;

public class MatchView {

    private static MatchView matchView;

    private MatchView() {
    }

    public static MatchView getInstance() {
        if (matchView == null) {
            matchView = new MatchView();
        }
        return matchView;
    }

    public void showMatchScoreBoard(Match matchPlayed, HashMap<Integer, Integer> runsScoredMap,
                                    HashMap<Integer, Integer> wicketsTakenMap) {
        Utility.printAndNextLine("Match Summary :");

        // first innings
        Utility.printAndNextLine("\nInnings 1 - " + matchPlayed.getFirstInnings().getRunsScored() + "/" +
                                 matchPlayed.getFirstInnings().getWicketsLost() + " (" +
                                 matchPlayed.getTeamA().getTeamName() + " batted & " +
                                 matchPlayed.getTeamB().getTeamName() + " bowled)");
        showInningsSummary(matchPlayed.getTeamA(), runsScoredMap, matchPlayed.getTeamB(), wicketsTakenMap);

        // second innings
        Utility.printAndNextLine("\nInnings 2 - " + matchPlayed.getSecondInnings().getRunsScored() + "/" +
                                 matchPlayed.getSecondInnings().getWicketsLost() + " (" +
                                 matchPlayed.getTeamB().getTeamName() + " batted & " +
                                 matchPlayed.getTeamA().getTeamName() + " bowled)");
        showInningsSummary(matchPlayed.getTeamB(), runsScoredMap, matchPlayed.getTeamA(), wicketsTakenMap);

        if (matchPlayed.getFirstInnings().getRunsScored() > matchPlayed.getSecondInnings().getRunsScored()) {
            Utility.printAndNextLine("\nHurray, " + matchPlayed.getTeamA().getTeamName() + " wins !");
        } else if (matchPlayed.getFirstInnings().getRunsScored() < matchPlayed.getSecondInnings().getRunsScored()) {
            Utility.printAndNextLine("\nHurray, " + matchPlayed.getTeamB().getTeamName() + " wins !");
        } else {
            Utility.printAndNextLine("\nThe match as ended a tie !");
        }
    }

    private void showInningsSummary(Team battingTeam, HashMap<Integer, Integer> runsScoredMap, Team bowlingTeam,
                                    HashMap<Integer, Integer> wicketsTakenMap) {
        Utility.printAndNextLine("\t............................................");
        Utility.printAndNextLine("\t|          Batsman         |  Runs Scored  |");
        Utility.printAndNextLine("\t|--------------------------|---------------|");
        for (int playerId : battingTeam.getPlayerIds()) {
            if (runsScoredMap.containsKey(playerId)) {
                Utility.printAndNextLine("\t|" + Utility.centerAlignString(26,
                        Repository.getInstance().getPlayerById(playerId).getPlayerName()) + "|" +
                                         Utility.centerAlignString(15, String.valueOf(runsScoredMap.get(playerId))) +
                                         "|");
            }
        }
        Utility.printAndNextLine("\t|--------------------------|---------------|");
        Utility.printAndNextLine("\t|          Bowler          | Wickets Taken |");
        Utility.printAndNextLine("\t|--------------------------|---------------|");
        for (int playerId : bowlingTeam.getPlayerIds()) {
            if (wicketsTakenMap.containsKey(playerId) && wicketsTakenMap.get(playerId) != 0) {
                Utility.printAndNextLine("\t|" + Utility.centerAlignString(26,
                        Repository.getInstance().getPlayerById(playerId).getPlayerName()) + "|" +
                                         Utility.centerAlignString(15, String.valueOf(wicketsTakenMap.get(playerId))) +
                                         "|");
            }
        }
        Utility.printAndNextLine("\t|------------------------------------------|");
    }
}
