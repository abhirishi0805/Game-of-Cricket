package com.tekion.gameofcricket.repositories;

import com.tekion.gameofcricket.models.Stat;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is the repository to access stats related data
 */
@Repository
public interface StatRepository extends MongoRepository<Stat, ObjectId> {
    List<Stat> findPlayerMatchStatsByPlayerId(ObjectId playerId);

    Stat findPlayerMatchStatByPlayerIdAndMatchId(ObjectId playerId, ObjectId matchId);

    List<Stat> findPlayerMatchStatsByMatchId(ObjectId matchId);
}