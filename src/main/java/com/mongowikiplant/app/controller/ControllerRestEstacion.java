package com.mongowikiplant.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongowikiplant.app.entity.Estacion;
import com.mongowikiplant.app.exception.NotFoundException;
import com.mongowikiplant.app.repository.EstacionRepository;

@RestController
@RequestMapping("/estaciones")
public class ControllerRestEstacion {

    @Autowired
    private EstacionRepository estacionRepository;

    @GetMapping
    public List<Estacion> listarEstaciones() {
        return estacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Estacion getEstacionById(@PathVariable String id) {
        return estacionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estación no encontrada"));
    }

    @PostMapping
    public Estacion crearEstacion(@RequestBody Estacion estacion) {
        return estacionRepository.save(estacion);
    }

    @PostMapping("/map")
    public Estacion saveEstacionFromMap(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Estacion estacion = mapper.convertValue(body, Estacion.class);
        return estacionRepository.save(estacion);
    }

    @PutMapping("/{id}")
    public Estacion actualizarEstacion(@PathVariable String id, @RequestBody Estacion estacionActualizada) {
        estacionActualizada.setId(id);
        return estacionRepository.save(estacionActualizada);
    }

    @PutMapping("/map/{id}")
    public Estacion updateEstacionFromMap(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Estacion estacion = mapper.convertValue(body, Estacion.class);
        estacion.setId(id);
        return estacionRepository.save(estacion);
    }

    
	@DeleteMapping("/{id}")
	public Estacion deleteEstacion(@PathVariable String id) {
		Estacion estacion = estacionRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Estación no encontrada"));
		estacionRepository.deleteById(id);
		return estacion;
	}

}