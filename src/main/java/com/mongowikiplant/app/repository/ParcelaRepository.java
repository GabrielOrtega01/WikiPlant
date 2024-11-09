package com.mongowikiplant.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mongowikiplant.app.entity.Parcela;

public interface ParcelaRepository extends MongoRepository<Parcela, String> {
}
