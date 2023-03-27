package com.tekion.gameofcricket.models;

import lombok.Builder;
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
 * This is the model class to represent any player's performance stat of a particular match
 */
@Document(collection = "player_match_stats")
@Data
public final class Stat {

    @Id
    private ObjectId id;
    private ObjectId playerId;
    private ObjectId matchId;
    private ObjectId teamId;
    private ObjectId opponentTeamId;
    private int runsScored;
    private int ballsFaced;
    private int sixesHit;
    private int foursHit;
    private int ballsThrown;
    private int wicketsTaken;
    private int runsConceded;

    public Stat(ObjectId playerId, ObjectId teamId, ObjectId opponentTeamId, ObjectId matchId) {
        this.playerId = playerId;
        this.teamId = teamId;
        this.opponentTeamId = opponentTeamId;
        this.matchId = matchId;
    }

    @Override
    public String toString() {
        return "PlayerMatchStat{" + "id=" + id + ", playerId=" + playerId + ", matchId=" + matchId + ", teamId=" +
               teamId + ", opponentTeamId=" + opponentTeamId + ", runsScored=" + runsScored + ", ballsFaced=" +
               ballsFaced + ", sixesHit=" + sixesHit + ", foursHit=" + foursHit + ", ballsThrown=" + ballsThrown +
               ", wicketsTaken=" + wicketsTaken + ", runsConceded=" + runsConceded + '}';
    }
}
