package Views;

import Models.Team;
import Others.Utility;

import java.util.ArrayList;

public class TeamView {
    private static TeamView teamViewInstance;

    private TeamView() { }

    public static TeamView getInstance() {
        if (teamViewInstance == null) {
            teamViewInstance = new TeamView();
        }
        return teamViewInstance;
    }

    public void showTable(ArrayList<Team> teamsList) {
        Utility.printAndNextLine("\nPoints Table - \n\nTeam ID\t\tTeam Name\tWon\t\tLost\tDrawn");
        for (Team team : teamsList) {
            Utility.printAndNextLine(team.getTeamID() + "\t\t" + team.getTeamName() + "\t\t\t" + team.getGamesWon() + "\t\t" + team.getGamesLost() + "\t\t" + team.getGamesDrawn());
        }
    }

    public void showListOfTeams(ArrayList<Team> teamsList) {
        Utility.printAndNextLine("\nList of teams : ");
        for (int i = 0; i < teamsList.size(); ++i) {
            Utility.printAndNextLine("" + (i + 1) + " --> " + teamsList.get(i).getTeamName());
        }
    }
}
