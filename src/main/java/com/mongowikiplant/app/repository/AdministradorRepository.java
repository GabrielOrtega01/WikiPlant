package com.mongowikiplant.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongowikiplant.app.entity.Administrador;

public interface AdministradorRepository extends MongoRepository<Administrador, String> {

}
