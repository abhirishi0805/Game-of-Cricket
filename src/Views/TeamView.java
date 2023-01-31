package Views;

import Models.Team;

import java.util.ArrayList;

public class TeamView {
    private static TeamView instance;

    private TeamView() { }

    public static TeamView getInstance() {
        if(instance == null) {
            instance = new TeamView();
        }
        return instance;
    }

    public void showTable(ArrayList<Team> teamsList) {
        System.out.println("\nPoints Table - \n");
        System.out.println("\033[0;1m" + "Team ID\t\tTeam Name\tWon\t\tLost\tDrawn");
        for(Team team : teamsList) {
            System.out.println(team.getTeamID() + "\t\t" + team.getTeamName() + "\t\t\t" + team.getGamesWon() + "\t\t" + team.getGamesLost() + "\t\t" + team.getGamesDrawn());
        }
    }

    public void showListOfTeams(ArrayList<Team> teamsList) {
        System.out.println("\033[0;1m" + "\nList of teams : ");
        for(int i = 0; i < teamsList.size(); ++i) {
            System.out.println("" + (i+1) + ". " + teamsList.get(i).getTeamName());
        }
    }
}
