package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Fotoperiodo;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.FotoperiodoRepository;

@RestController
@RequestMapping("/fotoperiodos")
public class ControllerRestFotoperiodo {

    @Autowired
    private FotoperiodoRepository fotoperiodoRepository;

    @GetMapping
    public List<Fotoperiodo> listarFotoperiodos() {
        return fotoperiodoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Fotoperiodo getFotoperiodoById(@PathVariable String id) {
        return fotoperiodoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fotoperíodo no encontrado"));
    }

    @PostMapping
    public Fotoperiodo crearFotoperiodo(@RequestBody Fotoperiodo fotoperiodo) {
        return fotoperiodoRepository.save(fotoperiodo);
    }

    @PostMapping("/map")
    public Fotoperiodo saveFotoperiodoFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Fotoperiodo fotoperiodo = mapper.convertValue(body, Fotoperiodo.class);
        return fotoperiodoRepository.save(fotoperiodo);
    }

    @PutMapping("/{id}")
    public Fotoperiodo actualizarFotoperiodo(@PathVariable String id, @RequestBody Fotoperiodo fotoperiodoActualizada) {
        fotoperiodoActualizada.setId(id);
        return fotoperiodoRepository.save(fotoperiodoActualizada);
    }

    @PutMapping("/map/{id}")
    public Fotoperiodo updateFotoperiodoFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Fotoperiodo fotoperiodo = mapper.convertValue(body, Fotoperiodo.class);
        fotoperiodo.setId(id);
        return fotoperiodoRepository.save(fotoperiodo);
    }

    
	@DeleteMapping("/{id}")
	public Fotoperiodo deleteFotoperiodo(@PathVariable String id) {
		Fotoperiodo fotoperiodo = fotoperiodoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Fotoperíodo no encontrado"));
		fotoperiodoRepository.deleteById(id);
		return fotoperiodo;
	}

}
