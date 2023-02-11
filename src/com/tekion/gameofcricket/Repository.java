package com.tekion.gameofcricket;

import com.tekion.gameofcricket.models.Player;
import com.tekion.gameofcricket.models.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Repository {

    private volatile static Repository repository;
    private HashMap<Integer, Player> playerMap;
    private HashMap<Integer, Team> teamMap;

    private static int nextPlayerId = (int) 1e3;
    private static int nextTeamId = (int) 1e6;

    private Repository() {
        playerMap = new HashMap<>();
        teamMap = new HashMap<>();
        fetchDataFromDatabase();
    }

    public static Repository getInstance() {
        if (repository == null) {
            synchronized (Repository.class) {
                if (repository == null) {
                    repository = new Repository();
                }
            }
        }
        return repository;
    }

    private void fetchDataFromDatabase() {
        // hardcoded dummy data for now
        getDataForTeam1();
        getDataForTeam2();
        getDataForTeam3();
    }

    private void getDataForTeam3() {
        Player player_3_1 = new Player(nextPlayerId++, "Virat Kohli", new ArrayList<>(List.of(45, 80, 105, 46, 76)),
                new ArrayList<>(List.of(0, 0, 1, 1, 0)));
        playerMap.put(player_3_1.getPlayerId(), player_3_1);
        Player player_3_2 = new Player(nextPlayerId++, "Dinesh Karthik", new ArrayList<>(List.of(25, 30, 55, 26, 34)),
                new ArrayList<>(List.of(0, 0, 0, 0, 0)));
        playerMap.put(player_3_2.getPlayerId(), player_3_2);
        Player player_3_3 = new Player(nextPlayerId++, "Mohammad Siraj", new ArrayList<>(List.of(12, 1, 0, 23, 8)),
                new ArrayList<>(List.of(2, 2, 0, 1, 2)));
        playerMap.put(player_3_3.getPlayerId(), player_3_3);
        Team team_3 = new Team(nextTeamId++, "RCB",
                new ArrayList<>(List.of(player_3_1.getPlayerId(), player_3_2.getPlayerId(), player_3_3.getPlayerId())),
                3, 2, 0, 6);
        teamMap.put(team_3.getTeamId(), team_3);
    }

    private void getDataForTeam2() {
        Player player_2_1 = new Player(nextPlayerId++, "MS Dhoni", new ArrayList<>(List.of(30, 40, 35, 26, 42)),
                new ArrayList<>(List.of(0, 0, 0, 0, 0)));
        playerMap.put(player_2_1.getPlayerId(), player_2_1);
        Player player_2_2 = new Player(nextPlayerId++, "Dwyane Brave", new ArrayList<>(List.of(5, 0, 11, 0, 24)),
                new ArrayList<>(List.of(2, 1, 2, 1, 1)));
        playerMap.put(player_2_2.getPlayerId(), player_2_2);
        Player player_2_3 = new Player(nextPlayerId++, "Ravindra Jadeja", new ArrayList<>(List.of(40, 30, 20, 35, 15)),
                new ArrayList<>(List.of(1, 2, 0, 1, 2)));
        playerMap.put(player_2_3.getPlayerId(), player_2_3);
        Team team_2 = new Team(nextTeamId++, "CSK",
                new ArrayList<>(List.of(player_2_1.getPlayerId(), player_2_2.getPlayerId(), player_2_3.getPlayerId())),
                4, 0, 1, 9);
        teamMap.put(team_2.getTeamId(), team_2);
    }

    private void getDataForTeam1() {
        Player player_1_1 = new Player(nextPlayerId++, "Rohit Sharma", new ArrayList<>(List.of(40, 80, 65, 6, 72)),
                new ArrayList<>(List.of(0, 0, 0, 1, 0)));
        playerMap.put(player_1_1.getPlayerId(), player_1_1);
        Player player_1_2 = new Player(nextPlayerId++, "Suryakumar Yadav", new ArrayList<>(List.of(25, 50, 110, 76, 4)),
                new ArrayList<>(List.of(0, 0, 0, 0, 0)));
        playerMap.put(player_1_2.getPlayerId(), player_1_2);
        Player player_1_3 = new Player(nextPlayerId++, "Jasprit Bumrah", new ArrayList<>(List.of(0, 10, 0, 36, 2)),
                new ArrayList<>(List.of(3, 2, 0, 1, 2)));
        playerMap.put(player_1_3.getPlayerId(), player_1_3);
        Team team_1 = new Team(nextTeamId++, "MI",
                new ArrayList<>(List.of(player_1_1.getPlayerId(), player_1_2.getPlayerId(), player_1_3.getPlayerId())),
                3, 1, 1, 7);
        teamMap.put(team_1.getTeamId(), team_1);
    }

    public List<Player> getPlayersList() {
        return new ArrayList<>(playerMap.values());
    }

    public Player getPlayerById(int playerId) {
        return playerMap.get(playerId);
    }

    public List<Team> getTeamsList() {
        return new ArrayList<>(teamMap.values());
    }

    public Team getTeamById(int teamId) {
        return teamMap.get(teamId);
    }
}
