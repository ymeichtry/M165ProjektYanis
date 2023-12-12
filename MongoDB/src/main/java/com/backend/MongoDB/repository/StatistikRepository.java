package com.backend.MongoDB.repository;

import com.backend.MongoDB.model.Statistik;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatistikRepository extends MongoRepository<Statistik, String> {
}
