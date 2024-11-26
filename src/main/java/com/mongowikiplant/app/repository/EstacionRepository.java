package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.Estacion;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstacionRepository extends MongoRepository<Estacion, String> {
}
