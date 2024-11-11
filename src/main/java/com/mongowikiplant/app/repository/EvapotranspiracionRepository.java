package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.Evapotranspiracion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EvapotranspiracionRepository extends MongoRepository<Evapotranspiracion, String> {
}
