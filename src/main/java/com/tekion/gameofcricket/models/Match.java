package com.tekion.gameofcricket.models;

import com.tekion.gameofcricket.utility.MatchResult;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "matches")
public class Match {

    @Id
    private ObjectId id;
    private ObjectId team1Id;
    private ObjectId team2Id;
    @Indexed
    private Date matchDate;
    private MatchResult result;

    public Match() {
    }

    public Match(ObjectId id, ObjectId team1Id, ObjectId team2Id, Date matchDate, MatchResult result) {
        this.id = id;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.matchDate = matchDate;
        this.result = result;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(ObjectId team1Id) {
        this.team1Id = team1Id;
    }

    public ObjectId getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(ObjectId team2Id) {
        this.team2Id = team2Id;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }
}
