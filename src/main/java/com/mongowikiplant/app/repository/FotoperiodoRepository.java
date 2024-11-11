package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.Fotoperiodo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FotoperiodoRepository extends MongoRepository<Fotoperiodo, String> {
    List<Fotoperiodo> findByEstacionId(String estacionId);
}
