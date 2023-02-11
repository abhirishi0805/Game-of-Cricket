package com.tekion.gameofcricket.services;

import com.tekion.gameofcricket.models.Team;
import com.tekion.gameofcricket.Repository;
import com.tekion.gameofcricket.others.Utility;
import com.tekion.gameofcricket.views.TeamView;

import java.util.Comparator;
import java.util.List;

public class TeamService {

    private static TeamService teamService;

    private TeamService() {
    }

    public static TeamService getInstance() {
        if (teamService == null) {
            teamService = new TeamService();
        }
        return teamService;
    }

    private int getTotalGamesPlayed(Team team) {
        return team.getGamesWon() + team.getGamesLost() + team.getGamesDrawn();
    }

    public void showPointsTable(List<Team> teamsList) {
        if (teamsList.size() == 0) {
            Utility.printAndNextLine("No team data to show !");
            return;
        }
        sortByPoints(teamsList);
        TeamView.getInstance().showPointsTable(teamsList);
    }

    private void sortByPoints(List<Team> teamsList) {
        class SortByPointsComparator implements Comparator<Team> {

            @Override
            public int compare(Team team1, Team team2) {
                if (team1.getPoints() != team2.getPoints()) {
                    return team2.getPoints() - team1.getPoints();
                }
                // if points are equal then the team with lesser number of games played goes on top
                return getTotalGamesPlayed(team1) - getTotalGamesPlayed(team2);
            }
        }
        teamsList.sort(new SortByPointsComparator());
    }

    public void incrementGamesWon(int teamId) {
        Team team = Repository.getInstance().getTeamById(teamId);
        team.setGamesWon(team.getGamesWon() + 1);
    }

    public void incrementGamesLost(int teamId) {
        Team team = Repository.getInstance().getTeamById(teamId);
        team.setGamesLost(team.getGamesLost() + 1);
    }

    public void incrementGamesDrawn(int teamId) {
        Team team = Repository.getInstance().getTeamById(teamId);
        team.setGamesDrawn(team.getGamesDrawn() + 1);
    }

    public void updateTeamPoints(int teamId) {
        Team team = Repository.getInstance().getTeamById(teamId);
        team.setPoints(2 * team.getGamesWon() + team.getGamesDrawn());
    }
}
