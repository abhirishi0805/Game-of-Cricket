package com.tekion.gameofcricket.models;

import com.tekion.gameofcricket.utility.enums.MatchResult;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This is the model class to represent a match
 * Not a final class as in future it can be extended to One Day Match, T20 Match, Test Match
 */
@Document(collection = "matches")
@Data
public class Match {

    @Id
    private ObjectId id;
    private ObjectId team1Id;
    private ObjectId team2Id;
    private String matchDate;
    private MatchResult result;

    public Match() { }

    public Match(ObjectId id, ObjectId team1Id, ObjectId team2Id, String matchDate) {
        this.id = id;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.matchDate = matchDate;
    }

    @Override
    public String toString() {
        return "Match {" + "id=" + id + ", team1Id=" + team1Id + ", team2Id=" + team2Id + ", matchDate='" + matchDate
                + '\'' + ", result=" + result + '}';
    }
}
