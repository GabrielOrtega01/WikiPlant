package com.mongowikiplant.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongowikiplant.app.entity.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
	@Query("SELECT c FROM Cliente c ORDER BY e.id DESC") 
    List<Cliente> findTop3ByOrderByPuntajeDesc();

}
