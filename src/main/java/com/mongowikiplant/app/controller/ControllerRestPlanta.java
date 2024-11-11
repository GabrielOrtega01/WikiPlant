package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Planta;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.PlantaRepository;

@RestController
@RequestMapping("/plantas")
public class ControllerRestPlanta {

    @Autowired
    private PlantaRepository plantaRepository;

    @GetMapping
    public List<Planta> listarPlantas() {
        return plantaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Planta getPlantaById(@PathVariable String id) {
        return plantaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Planta no encontrada"));
    }

    @PostMapping
    public Planta crearPlanta(@RequestBody Planta planta) {
        return plantaRepository.save(planta);
    }

    @PostMapping("/map")
    public Planta savePlantaFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Planta planta = mapper.convertValue(body, Planta.class);
        return plantaRepository.save(planta);
    }

    @PutMapping("/{id}")
    public Planta actualizarPlanta(@PathVariable String id, @RequestBody Planta plantaActualizada) {
        plantaActualizada.setId(id);
        return plantaRepository.save(plantaActualizada);
    }

    @PutMapping("/map/{id}")
    public Planta updatePlantaFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Planta planta = mapper.convertValue(body, Planta.class);
        planta.setId(id);
        return plantaRepository.save(planta);
    }

    
	@DeleteMapping("/{id}")
	public Planta deletePlanta(@PathVariable String id) {
		Planta planta = plantaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Planta no encontrada"));
		plantaRepository.deleteById(id);
		return planta;
	}

}
