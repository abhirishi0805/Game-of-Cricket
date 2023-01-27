import java.util.ArrayList;

class Team {
    private final int TEAM_SIZE = 3;
    private String name;
    private ArrayList<Player> players;
    private int runsScored, wicketsLost;

    public Team() {
        players = new ArrayList<>();
        runsScored = 0;
        wicketsLost = 0;
        formTeam();
    }

    public int getTEAM_SIZE() {
        return TEAM_SIZE;
    }

    public String getName() {
        return name;
    }

    public Player getCurrentBatsman() {
        return players.get(wicketsLost);
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getWicketsLost() {
        return wicketsLost;
    }

    public void setWicketsLost(int wicketsLost) {
        this.wicketsLost = wicketsLost;
    }

    private void formTeam() {
        // get team name
        name = Utility.getUserInput("Team name : ");

        // get player details
        while(players.size() < TEAM_SIZE) {
            String playerName = Utility.getUserInput("Player " + (players.size() + 1) + " - ");
            Player player = new Player(playerName);
            players.add(player);
        }
    }
}
