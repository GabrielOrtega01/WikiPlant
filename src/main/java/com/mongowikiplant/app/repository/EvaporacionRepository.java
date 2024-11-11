package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.Evaporacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EvaporacionRepository extends MongoRepository<Evaporacion, String> {
}
