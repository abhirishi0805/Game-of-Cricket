package com.tekion.gameofcricket.repositories;

import com.tekion.gameofcricket.models.PlayerMatchStat;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerMatchStatRepository extends MongoRepository<PlayerMatchStat, ObjectId> {

}