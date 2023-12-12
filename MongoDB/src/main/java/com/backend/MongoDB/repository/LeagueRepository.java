package com.backend.MongoDB.repository;

import com.backend.MongoDB.model.League;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends MongoRepository<League, String> {
}
