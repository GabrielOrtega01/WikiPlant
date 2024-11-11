package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.Precipitacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrecipitacionRepository extends MongoRepository<Precipitacion, String> {
}
