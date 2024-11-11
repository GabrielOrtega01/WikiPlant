package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.DeficitAgua;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeficitAguaRepository extends MongoRepository<DeficitAgua, String> {
}
