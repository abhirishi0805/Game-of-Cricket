package com.tekion.gameofcricket.views;

import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.others.Utility;

import java.util.List;

public class TeamView {

    private static TeamView teamView;

    private TeamView() {
    }

    public static TeamView getInstance() {
        if (teamView == null) {
            teamView = new TeamView();
        }
        return teamView;
    }

    public void showPointsTable(List<Team> teamsList) {
        Utility.printAndNextLine("\nPoints Table - \n\nTeam Id\t\tTeam Name\tWon\t\tLost\tDrawn\tPoints");
        for (Team team : teamsList) {
            Utility.printAndNextLine(
                    team.getTeamId() + "\t\t" + team.getTeamName() + "\t\t\t" + team.getGamesWon() + "\t\t" +
                    team.getGamesLost() + "\t\t" + team.getGamesDrawn() + "\t\t" + team.getPoints());
        }
    }

    public void showListOfTeams(List<Team> teamsList) {
        Utility.printAndNextLine("\nList of teams : ");
        for (int i = 0; i < teamsList.size(); ++i) {
            Utility.printAndNextLine("" + (i + 1) + " --> " + teamsList.get(i).getTeamName());
        }
    }
}
