package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.Amplitud;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AmplitudRepository extends MongoRepository<Amplitud, String> {
}
