package com.mongowikiplant.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongowikiplant.app.entity.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
	@Query("SELECT e FROM Estudiante e ORDER BY e.id DESC") // Cambia 'puntaje' al nombre correcto del campo que guarda la puntuación
    List<Cliente> findTop3ByOrderByPuntajeDesc();

}
