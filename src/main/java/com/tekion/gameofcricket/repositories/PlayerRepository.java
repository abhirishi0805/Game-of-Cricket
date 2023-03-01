package com.tekion.gameofcricket.repositories;

import com.tekion.gameofcricket.models.Player;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, ObjectId> {

}