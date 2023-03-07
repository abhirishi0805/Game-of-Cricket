package com.tekion.gameofcricket.models;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "players")
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Player {

    @Id
    private ObjectId id;
    private String playerName;
    private int totalRunsScored = 0;
    private int totalWicketsTaken = 0;
    private int gamesPlayed = 0;

    public Player() {
    }

    public Player(ObjectId id, String playerName, int totalRunsScored, int totalWicketsTaken, int gamesPlayed) {
        this.id = id;
        this.playerName = playerName;
        this.totalRunsScored = totalRunsScored;
        this.totalWicketsTaken = totalWicketsTaken;
        this.gamesPlayed = gamesPlayed;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getTotalRunsScored() {
        return totalRunsScored;
    }

    public void setTotalRunsScored(int totalRunsScored) {
        this.totalRunsScored = totalRunsScored;
    }

    public int getTotalWicketsTaken() {
        return totalWicketsTaken;
    }

    public void setTotalWicketsTaken(int totalWicketsTaken) {
        this.totalWicketsTaken = totalWicketsTaken;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    @Override
    public String toString() {
        return "Player{id=" + id + ", playerName='" + playerName + '\'' + ", gamesPlayed=" + gamesPlayed +
               ", totalRunsScored=" + totalRunsScored + ", totalWicketsTaken=" + totalWicketsTaken + '}';
    }
}
