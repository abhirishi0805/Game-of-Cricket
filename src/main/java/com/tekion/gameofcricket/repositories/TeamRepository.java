package com.tekion.gameofcricket.repositories;

import com.tekion.gameofcricket.models.Team;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the repository to access team related data
 */
@Repository
public interface TeamRepository extends MongoRepository<Team, ObjectId> {

    Team getTeamByTeamNameEqualsIgnoreCase(String teamName);
}