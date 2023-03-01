package com.tekion.gameofcricket.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player_match_stats")
public class PlayerMatchStat {

    @Id
    private ObjectId id;
    @Indexed
    private ObjectId playerId;
    @Indexed
    private ObjectId matchId;
    private ObjectId teamId;
    private int runsScored;
    private int ballsFaced;
    private int sixesHit;
    private int foursHit;
    private int ballsThrown;
    private int wicketsTaken;
    private int runsConceded;

    public PlayerMatchStat() {
    }

    public PlayerMatchStat(ObjectId playerId, ObjectId matchId, ObjectId teamId) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.teamId = teamId;
        runsScored = 0;
        ballsFaced = 0;
        sixesHit = 0;
        foursHit = 0;
        ballsThrown = 0;
        wicketsTaken = 0;
        runsConceded = 0;
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
}
