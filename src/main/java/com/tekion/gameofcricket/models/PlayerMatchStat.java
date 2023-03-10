package com.tekion.gameofcricket.models;

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
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class PlayerMatchStat {

    @Id
    private ObjectId id;
    private ObjectId playerId;
    private ObjectId matchId;
    private ObjectId teamId;
    // TODO - add opponent team ID
    private int runsScored;
    private int ballsFaced;
    private int sixesHit;
    private int foursHit;
    private int ballsThrown;
    private int wicketsTaken;
    private int runsConceded;

    public PlayerMatchStat() {
    }

    public PlayerMatchStat(ObjectId playerId, ObjectId matchId, ObjectId teamId, int runsScored, int ballsFaced,
                           int sixesHit, int foursHit, int ballsThrown, int wicketsTaken, int runsConceded) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.teamId = teamId;
        this.runsScored = runsScored;
        this.ballsFaced = ballsFaced;
        this.sixesHit = sixesHit;
        this.foursHit = foursHit;
        this.ballsThrown = ballsThrown;
        this.wicketsTaken = wicketsTaken;
        this.runsConceded = runsConceded;
    }

    public ObjectId getPlayerId() {
        return playerId;
    }

    public void setPlayerId(ObjectId playerId) {
        this.playerId = playerId;
    }

    public ObjectId getMatchId() {
        return matchId;
    }

    public void setMatchId(ObjectId matchId) {
        this.matchId = matchId;
    }

    public ObjectId getTeamId() {
        return teamId;
    }

    public void setTeamId(ObjectId teamId) {
        this.teamId = teamId;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public int getSixesHit() {
        return sixesHit;
    }

    public void setSixesHit(int sixesHit) {
        this.sixesHit = sixesHit;
    }

    public int getFoursHit() {
        return foursHit;
    }

    public void setFoursHit(int foursHit) {
        this.foursHit = foursHit;
    }

    public int getBallsThrown() {
        return ballsThrown;
    }

    public void setBallsThrown(int ballsThrown) {
        this.ballsThrown = ballsThrown;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public int getRunsConceded() {
        return runsConceded;
    }

    public void setRunsConceded(int runsConceded) {
        this.runsConceded = runsConceded;
    }

    @Override
    public String toString() {
        return "PlayerMatchStat{" + "id=" + id + ", playerId=" + playerId + ", matchId=" + matchId + ", teamId=" +
               teamId + ", runsScored=" + runsScored + ", ballsFaced=" + ballsFaced + ", sixesHit=" + sixesHit +
               ", foursHit=" + foursHit + ", ballsThrown=" + ballsThrown + ", wicketsTaken=" + wicketsTaken +
               ", runsConceded=" + runsConceded + '}';
    }
}
