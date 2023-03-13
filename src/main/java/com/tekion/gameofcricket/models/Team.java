package com.tekion.gameofcricket.models;

import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
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

    @Override
    public String toString() {
        return "Team{id=" + id + ", teamName='" + teamName + '\'' + ", gamesWon=" + gamesWon + ", gamesDrawn=" +
               gamesDrawn + ", gamesLost=" + gamesLost + ", playerIds=" + playerIds + '}';
    }
}
