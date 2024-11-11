package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.Estimacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstimacionRepository extends MongoRepository<Estimacion, String> {
}
