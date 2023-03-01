package com.tekion.gameofcricket.helper;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PlayMatchBody {

    private ObjectId team1Id;
    private ObjectId team2Id;

    public PlayMatchBody() {
    }

    public PlayMatchBody(ObjectId team1Id, ObjectId team2Id) {
        this.team1Id = team1Id;
        this.team2Id = team2Id;
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
}
