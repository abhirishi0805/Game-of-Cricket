package com.tekion.gameofcricket.services;

import org.bson.types.ObjectId;

public interface PlayMatchService {

    void playMatch(ObjectId team1Id, ObjectId team2Id);
}
