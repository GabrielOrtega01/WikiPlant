package com.mongowikiplant.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mongowikiplant.app.entity.Cultivo;

public interface CultivoRepository extends MongoRepository<Cultivo, String> {
}
