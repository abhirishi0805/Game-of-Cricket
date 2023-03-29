package com.tekion.gameofcricket.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * This is the model class to represent a team
 */
@Document(collection = "teams")
@Data
public final class Team {

    @Id
    private ObjectId id;
    private String teamName;
    private List<ObjectId> playerIds;
    private int gamesWon;
    private int gamesDrawn;
    private int gamesLost;

    public Team() {
    }

    public Team(String teamName, List<ObjectId> playerIds) {
        this.teamName = teamName;
        this.playerIds = playerIds;
    }

    @Override
    public String toString() {
        return "Team {id=" + id + ", teamName='" + teamName + '\'' + ", gamesWon=" + gamesWon + ", gamesDrawn=" +
               gamesDrawn + ", gamesLost=" + gamesLost + ", playerIds=" + playerIds + '}';
    }
}
