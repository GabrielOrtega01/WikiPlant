package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.Aguachinamiento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AguachinamientoRepository extends MongoRepository<Aguachinamiento, String> {
}
