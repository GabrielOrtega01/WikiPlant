package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.Tminmedia;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TminmediaRepository extends MongoRepository<Tminmedia, String>  {
    List<Tminmedia> findByEstacionId(String estacionId);
}