package com.tekion.gameofcricket.models;

import lombok.Data;
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
@Data
public final class Player {

    @Id
    private ObjectId id;
    private String playerName;
    private int totalRunsScored;
    private int totalWicketsTaken;
    private int gamesPlayed;

    public Player() {
    }

    public Player(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return "Player{id=" + id + ", playerName='" + playerName + '\'' + ", gamesPlayed=" + gamesPlayed +
               ", totalRunsScored=" + totalRunsScored + ", totalWicketsTaken=" + totalWicketsTaken + '}';
    }
}
