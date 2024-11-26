package com.mongowikiplant.app.controller;

import com.mongowikiplant.app.entity.Fotoperiodo;
import com.mongowikiplant.app.repository.FotoperiodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fotoperiodo")

public class ControllerRestFotoperiodo {

    @Autowired
    private FotoperiodoRepository fotoperiodoRepository;

    @GetMapping
    public List<Fotoperiodo> listarFotoperiodos() {
        return fotoperiodoRepository.findAll();
    }

    @PostMapping
    public Fotoperiodo crearFotoperiodo(@RequestBody Fotoperiodo fotoperiodo) {
        return fotoperiodoRepository.save(fotoperiodo);
    }

    @PutMapping("/{id}")
    public Fotoperiodo actualizarFotoperiodo(@PathVariable String id, @RequestBody Fotoperiodo fotoperiodoActualizado) {
    	fotoperiodoActualizado.setId(id);
        return fotoperiodoRepository.save(fotoperiodoActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarFotoperiodo(@PathVariable String id) {
    	fotoperiodoRepository.deleteById(id);
    }
}
