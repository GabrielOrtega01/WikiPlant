package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.Radiacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RadiacionRepository extends MongoRepository<Radiacion, String> {
}
