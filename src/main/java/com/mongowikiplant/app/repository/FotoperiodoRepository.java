package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.Fotoperiodo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FotoperiodoRepository extends MongoRepository<Fotoperiodo, String> {
}
