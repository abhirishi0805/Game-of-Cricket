package com.tekion.gameofcricket.models;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This is the model class to represent a team
 */
@Document(collection = "teams")
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class Team {

    @Id
    private ObjectId id;
    private String teamName;
    private List<ObjectId> playerIds;
    private int gamesWon = 0;
    private int gamesDrawn = 0;
    private int gamesLost = 0;

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

    @Override
    public String toString() {
        return "Team{id=" + id + ", teamName='" + teamName + '\'' + ", gamesWon=" + gamesWon + ", gamesDrawn=" +
               gamesDrawn + ", gamesLost=" + gamesLost + ", playerIds=" + playerIds + '}';
    }
}
