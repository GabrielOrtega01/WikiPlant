package com.mongowikiplant.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mongowikiplant.app.entity.Estacion;

public interface EstacionRepository extends MongoRepository<Estacion, String> {
    // Aquí no es necesario definir métodos adicionales si solo necesitas las operaciones CRUD básicas
}
