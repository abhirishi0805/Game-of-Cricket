package Others;

import Models.Player;
import Models.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Repository {
    private static Repository instance;
    private HashMap<Integer, Player> playerMap;
    private HashMap<Integer, Team> teamMap;

    private static int nextPlayerID = (int) 1e3;
    private static int nextTeamID = (int) 1e6;

    private Repository() {
        playerMap = new HashMap<>();
        teamMap = new HashMap<>();
        fetchDataFromDatabase();
    }

    public static Repository getInstance() {
        if(instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    private void fetchDataFromDatabase() {
        // hardcoded dummy data for now

        // team 1
        Player player_1_1 = new Player(nextPlayerID++, "Rohit Sharma", new ArrayList<>(List.of(40, 80, 65, 6, 72)), new ArrayList<>(List.of(0, 0, 0, 1, 0)));
        playerMap.put(player_1_1.getPlayerID(), player_1_1);
        Player player_1_2 = new Player(nextPlayerID++, "Suryakumar Yadav", new ArrayList<>(List.of(25, 50, 110, 76, 4)), new ArrayList<>(List.of(0, 0, 0, 0, 0)));
        playerMap.put(player_1_2.getPlayerID(), player_1_2);
        Player player_1_3 = new Player(nextPlayerID++, "Jasprit Bumrah", new ArrayList<>(List.of(0, 10, 0, 36, 2)), new ArrayList<>(List.of(3, 2, 0, 1, 2)));
        playerMap.put(player_1_3.getPlayerID(), player_1_3);
        Team team_1 = new Team(nextTeamID++, "MI", new ArrayList<>(List.of(player_1_1.getPlayerID(), player_1_2.getPlayerID(), player_1_3.getPlayerID())), 3, 1, 1);
        teamMap.put(team_1.getTeamID(), team_1);

        // team 2
        Player player_2_1 = new Player(nextPlayerID++, "MS Dhoni", new ArrayList<>(List.of(30, 40, 35, 26, 42)), new ArrayList<>(List.of(0, 0, 0, 0, 0)));
        playerMap.put(player_2_1.getPlayerID(), player_2_1);
        Player player_2_2 = new Player(nextPlayerID++, "Dwyane Brave", new ArrayList<>(List.of(5, 0, 11, 0, 24)), new ArrayList<>(List.of(2, 1, 2, 1, 1)));
        playerMap.put(player_2_2.getPlayerID(), player_2_2);
        Player player_2_3 = new Player(nextPlayerID++, "Ravindra Jadeja", new ArrayList<>(List.of(40, 30, 20, 35, 15)), new ArrayList<>(List.of(1, 2, 0, 1, 2)));
        playerMap.put(player_2_3.getPlayerID(), player_2_3);
        Team team_2 = new Team(nextTeamID++, "CSK", new ArrayList<>(List.of(player_2_1.getPlayerID(), player_2_2.getPlayerID(), player_2_3.getPlayerID())), 4, 0, 1);
        teamMap.put(team_2.getTeamID(), team_2);

        // team 3
        Player player_3_1 = new Player(nextPlayerID++, "Virat Kohli", new ArrayList<>(List.of(45, 80, 105, 46, 76)), new ArrayList<>(List.of(0, 0, 1, 1, 0)));
        playerMap.put(player_3_1.getPlayerID(), player_3_1);
        Player player_3_2 = new Player(nextPlayerID++, "Dinesh Karthik", new ArrayList<>(List.of(25, 30, 55, 26, 34)), new ArrayList<>(List.of(0, 0, 0, 0, 0)));
        playerMap.put(player_3_2.getPlayerID(), player_3_2);
        Player player_3_3 = new Player(nextPlayerID++, "Mohammad Siraj", new ArrayList<>(List.of(12, 1, 0, 23, 8)), new ArrayList<>(List.of(2, 2, 0, 1, 2)));
        playerMap.put(player_3_3.getPlayerID(), player_3_3);
        Team team_3 = new Team(nextTeamID++, "RCB", new ArrayList<>(List.of(player_3_1.getPlayerID(), player_3_2.getPlayerID(), player_3_3.getPlayerID())), 3, 2, 0);
        teamMap.put(team_3.getTeamID(), team_3);
    }

    public ArrayList<Player> getPlayersList() {
        return new ArrayList<>(playerMap.values());
    }

    public Player getPlayerByID(int playerID) {
        return playerMap.get(playerID);
    }

    public ArrayList<Team> getTeamsList() {
        return new ArrayList<>(teamMap.values());
    }

    public Team getTeamByID(int teamID) {
        return teamMap.get(teamID);
    }
}
