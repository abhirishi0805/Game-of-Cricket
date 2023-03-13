package com.tekion.gameofcricket.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

/**
 * This is the model class to represent a player
 */
@Document(collection = "players")
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Getter
@Setter
public final class Player {

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

    @Override
    public String toString() {
        return "Player{id=" + id + ", playerName='" + playerName + '\'' + ", gamesPlayed=" + gamesPlayed +
               ", totalRunsScored=" + totalRunsScored + ", totalWicketsTaken=" + totalWicketsTaken + '}';
    }
}
