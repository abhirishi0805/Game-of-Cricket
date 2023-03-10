package com.tekion.gameofcricket.repositories;

import com.tekion.gameofcricket.models.Match;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is the repository to access match related data
 */
@Repository
public interface MatchRepository extends MongoRepository<Match, ObjectId> {

    List<Match> findMatchesByMatchDate(String date);

    List<Match> findMatchesByTeam1IdOrTeam2Id(ObjectId team1Id, ObjectId team2Id);
}