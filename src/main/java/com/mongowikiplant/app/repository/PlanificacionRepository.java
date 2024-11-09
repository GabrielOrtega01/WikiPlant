package com.mongowikiplant.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mongowikiplant.app.entity.Planificacion;

public interface PlanificacionRepository extends MongoRepository<Planificacion, String> {
}
