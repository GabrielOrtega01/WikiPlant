package com.mongowikiplant.app.repository;

import com.mongowikiplant.app.entity.Radiacion;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RadiacionRepository extends MongoRepository<Radiacion, String> {
  /*  List<Radiacion> findByEstacionId(String estacionId);*/
}