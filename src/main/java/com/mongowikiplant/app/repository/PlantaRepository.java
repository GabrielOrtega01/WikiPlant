package com.mongowikiplant.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongowikiplant.app.entity.Planta;

public interface PlantaRepository extends MongoRepository<Planta, String>{
	List<Planta> findTop5ByOrderByBusquedasDesc();
}
