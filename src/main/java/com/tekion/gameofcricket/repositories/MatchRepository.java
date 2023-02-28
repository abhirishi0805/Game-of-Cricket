package com.tekion.gameofcricket.repositories;

import com.tekion.gameofcricket.models.Match;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MatchRepository extends MongoRepository<Match, ObjectId> {

    public List<Match> findMatchesByMatchDate(Date date);

    public List<Match> findMatchesByTeam1IdOrTeam2Id(ObjectId teamId);
}
