package com.tekion.gameofcricket.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "teams")
public class Team {

    @Id
    private ObjectId id;
    private String teamName;
    private List<ObjectId> playerIds;
    private int gamesWon;
    private int gamesDrawn;
    private int gamesLost;

    public Team() {
    }

    public Team(ObjectId id, String teamName, List<ObjectId> playerIds, int gamesWon, int gamesDrawn, int gamesLost) {
        this.id = id;
        this.teamName = teamName;
        this.playerIds = playerIds;
        this.gamesWon = gamesWon;
        this.gamesDrawn = gamesDrawn;
        this.gamesLost = gamesLost;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<ObjectId> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(List<ObjectId> playerIds) {
        this.playerIds = playerIds;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesDrawn() {
        return gamesDrawn;
    }

    public void setGamesDrawn(int gamesDrawn) {
        this.gamesDrawn = gamesDrawn;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }
}
