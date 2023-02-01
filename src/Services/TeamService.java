package Services;

import Models.Team;
import Others.Repository;
import Others.Utility;
import Views.TeamView;

import java.util.ArrayList;
import java.util.Comparator;

public class TeamService {
    private static TeamService teamServiceInstance;

    private TeamService() { }

    public static TeamService getInstance() {
        if (teamServiceInstance == null) {
            teamServiceInstance = new TeamService();
        }
        return teamServiceInstance;
    }

    private int getTeamPoints(Team team) {
        // win = 2 points, draw = 1 point, loss = 0 point
        return 2 * team.getGamesWon() + team.getGamesDrawn();
    }

    private int getTotalGamesPlayed(Team team) {
        return team.getGamesWon() + team.getGamesLost() + team.getGamesDrawn();
    }

    public void showPointsTable(ArrayList<Team> teamsList) {
        if (teamsList.size() == 0) {
            Utility.printAndNextLine("No team data to show !");
            return;
        }
        sortByPoints(teamsList);
        TeamView.getInstance().showTable(teamsList);
    }

    private void sortByPoints(ArrayList<Team> teamsList) {
        class sortByPointsComparator implements Comparator<Team> {
            @Override
            public int compare(Team team1, Team team2) {
                if (getTeamPoints(team1) != getTeamPoints(team2))
                    return getTeamPoints(team2) - getTeamPoints(team1);
                // if points are equal then the team with lesser number of games played goes on top
                return getTotalGamesPlayed(team1) - getTotalGamesPlayed(team2);
            }
        }
        teamsList.sort(new sortByPointsComparator());
    }

    public void incrementGamesWon(int teamID) {
        Team team = Repository.getInstance().getTeamByID(teamID);
        team.setGamesWon(team.getGamesWon() + 1);
    }

    public void incrementGamesLost(int teamID) {
        Team team = Repository.getInstance().getTeamByID(teamID);
        team.setGamesLost(team.getGamesLost() + 1);
    }

    public void incrementGamesDrawn(int teamID) {
        Team team = Repository.getInstance().getTeamByID(teamID);
        team.setGamesDrawn(team.getGamesDrawn() + 1);
    }
}
