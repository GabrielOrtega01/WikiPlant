package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.Tmedia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TmediaRepository extends MongoRepository<Tmedia, String> {
}
