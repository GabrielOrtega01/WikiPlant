package com.mongowikiplant.app.controller;


import com.mongowikiplant.app.entity.Estacion;
import com.mongowikiplant.app.repository.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estacion")

public class ControllerRestEstacion {

	@Autowired
    private EstacionRepository estacionRepository;

    @GetMapping
    public List<Estacion> listarPlantas() {
        return estacionRepository.findAll();
    }

    @PostMapping
    public Estacion crearEstacion(@RequestBody Estacion estacion) {
        return estacionRepository.save(estacion);
    }

    @PutMapping("/{id}")
    public Estacion actualizarEstacion(@PathVariable String id, @RequestBody Estacion estacionActualizado) {
    	estacionActualizado.setId(id);
        return estacionRepository.save(estacionActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarPlanta(@PathVariable String id) {
    	estacionRepository.deleteById(id);
    }
    
}


